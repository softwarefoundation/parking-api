package com.softwarefoundation.parkingapi.web.controller;

import com.softwarefoundation.parkingapi.entity.dto.UsuarioLoginDto;
import com.softwarefoundation.parkingapi.jwt.JwtToken;
import com.softwarefoundation.parkingapi.jwt.JwtUserDetailsService;
import com.softwarefoundation.parkingapi.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoCotnroller {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@Valid @RequestBody UsuarioLoginDto usuarioLoginDto, HttpServletRequest request) {
        log.info("Processo de autenticação pelo login {}", usuarioLoginDto.getUsername());
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
            JwtToken token = jwtUserDetailsService.getTokenAuthenticated(usuarioLoginDto.getUsername());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            log.warn("Bad Credentials from username {}", usuarioLoginDto.getUsername());
        }
        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas"));
    }


}
