package com.softwarefoundation.parkingapi.service;

import com.softwarefoundation.parkingapi.entity.Usuario;
import com.softwarefoundation.parkingapi.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService extends AbstractService {

    private final IUsuarioRepository iUsuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return iUsuarioRepository.save(usuario);
    }
}
