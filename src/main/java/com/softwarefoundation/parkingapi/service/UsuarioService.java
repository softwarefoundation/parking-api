package com.softwarefoundation.parkingapi.service;

import com.softwarefoundation.parkingapi.entity.Usuario;
import com.softwarefoundation.parkingapi.enums.Role;
import com.softwarefoundation.parkingapi.exceptions.NegocioException;
import com.softwarefoundation.parkingapi.exceptions.UsernameUniqueViolationException;
import com.softwarefoundation.parkingapi.exceptions.UserEntityNotFoundException;
import com.softwarefoundation.parkingapi.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService extends AbstractService {

    private final IUsuarioRepository iUsuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return iUsuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return this.iUsuarioRepository.findById(id).orElseThrow(() -> new UserEntityNotFoundException(String.format("Usuário id=%s não encontrado", id)));
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
        if (!novaSenha.equals(confirmaSenha)) {
            throw new NegocioException("Nova senha não confere com confirmação de senha", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = this.buscarPorId(id);
        if (!passwordEncoder.matches(senhaAtual, usuario.getPassword())){
            throw new NegocioException("Sua senha não confere", HttpStatus.BAD_REQUEST);
        }
        usuario.setPassword(passwordEncoder.encode(novaSenha));
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return this.iUsuarioRepository.findAll();
    }

    public Usuario loadUserByUsername(final String username) {
        return this.iUsuarioRepository.findByUsername(username).orElseThrow(() -> new UserEntityNotFoundException(String.format("Usuário username=%s não encontrado", username)));
    }

    public Role findRoleByUsername(final String username) {
        return this.iUsuarioRepository.findRoleByUsername(username);
    }
}
