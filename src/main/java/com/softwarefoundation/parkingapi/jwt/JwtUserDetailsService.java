package com.softwarefoundation.parkingapi.jwt;

import com.softwarefoundation.parkingapi.entity.Usuario;
import com.softwarefoundation.parkingapi.enums.Role;
import com.softwarefoundation.parkingapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioService.loadUserByUsername(username);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(final String username) {
        Role role = this.usuarioService.findRoleByUsername(username);
        return JwtUtils.createTokenImplV2(username, role.name().substring("ROLE_".length()));
    }
}
