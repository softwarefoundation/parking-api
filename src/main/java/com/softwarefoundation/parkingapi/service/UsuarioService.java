package com.softwarefoundation.parkingapi.service;

import com.softwarefoundation.parkingapi.entity.Usuario;
import com.softwarefoundation.parkingapi.exceptions.UsuarioNaoEncontrado;
import com.softwarefoundation.parkingapi.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService extends AbstractService {

    private final IUsuarioRepository iUsuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return iUsuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return this.iUsuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontrado());
    }

    @Transactional
    public Usuario editarSenha(Long id, String password) {
        Usuario usuario = this.buscarPorId(id);
        usuario.setPassword(password);
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return this.iUsuarioRepository.findAll();
    }
}
