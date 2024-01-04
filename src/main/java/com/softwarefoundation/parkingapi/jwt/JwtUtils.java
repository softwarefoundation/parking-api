package com.softwarefoundation.parkingapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class JwtUtils {

    private static final String JWT_BEARER = "Bearer ";
    private static final String JWT_AUTHORIZATION = "Authorization";
    private static final String SECRET_KEYS = "3613d057-3a6d-4db0-83f3-f8bdb072f788";
    private static final long EXPIRE_DAYS = 0;
    private static final long EXPIRE_HOURS = 0;
    private static final long EXPIRE_MINUTES = 2;

    private JwtUtils() {
    }

    private static SecretKey generatePrivateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEYS.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date iat) {
        LocalDateTime localDateTime = iat.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime exp = localDateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(exp.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String username, String role) {
        Date iat = new Date();
        Date exp = toExpireDate(iat);

        String token = Jwts.builder().header()
                .add("typ", "JWT")
                .add("alg", "HS256").and()
                .claim("sub", username)
                .claim("iat", iat)
                .claim("exp", exp)
                .signWith(generatePrivateKey()).compact();
        return new JwtToken(token);
    }

    public static JwtToken createTokenImplV2(String username, String role) {
        Date iat = new Date();
        Date exp = toExpireDate(iat);
        SecretKey key = Jwts.SIG.HS256.key().build();


        String jwts = Jwts.builder()
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .issuer("parking-api")
                .subject(username)
                .expiration(exp)
                .notBefore(iat)
                .issuedAt(iat)
                .claim("role", role)
                .signWith(generatePrivateKey()).compact();
        return new JwtToken(jwts);
    }


    public static Claims getClaimsFromToken(final String token) {
        try {
            return Jwts.parser().verifyWith(generatePrivateKey()).build().parseSignedClaims(refactorToken(token)).getPayload();
        } catch (JwtException e) {
            log.error(String.format("Token inválido %s", e.getMessage()));
        }
        return null;
    }

    public static String getUsernameFromToken(final String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isValidToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parser().verifyWith(generatePrivateKey()).build().parseSignedClaims(refactorToken(token));
            return Objects.nonNull(claims);
        } catch (JwtException e) {
            log.error(String.format("Token inválido %s", e.getMessage()));
        }
        return false;
    }

    private static String refactorToken(final String token) {
        return token.contains(JWT_BEARER) ? token.replace(JWT_BEARER, "") : token;
    }

}
