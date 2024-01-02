package com.softwarefoundation.parkingapi.web.controller;

import com.softwarefoundation.parkingapi.entity.Usuario;
import com.softwarefoundation.parkingapi.entity.dto.UsuarioCreateDto;
import com.softwarefoundation.parkingapi.entity.dto.UsuarioResponseDto;
import com.softwarefoundation.parkingapi.entity.dto.UsuarioSenhaDto;
import com.softwarefoundation.parkingapi.entity.dto.mapper.UsuarioMapper;
import com.softwarefoundation.parkingapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController extends AbstractController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto usuarioCreateDto) {
        Usuario usuarioResponse = this.usuarioService.salvar(UsuarioMapper.toUsuario(usuarioCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(usuarioResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id) {
        Usuario usuarioResponse = this.usuarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toDto(usuarioResponse));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> upatePassword(@PathVariable Long id, @RequestBody UsuarioSenhaDto usuario) {
        this.usuarioService.editarSenha(id, usuario.getSenhaAtual(), usuario.getNovaSenha(), usuario.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuariosResponse = this.usuarioService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuariosResponse);
    }


}
