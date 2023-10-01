package com.softwarefoundation.parkingapi.web.controller;

import com.softwarefoundation.parkingapi.entity.Usuario;
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
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario usuarioResponse = this.usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> create(@PathVariable Long id) {
        Usuario usuarioResponse = this.usuarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> upatePassword(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioResponse = this.usuarioService.editarSenha(id, usuario.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(usuarioResponse);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuariosResponse = this.usuarioService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(usuariosResponse);
    }


}
