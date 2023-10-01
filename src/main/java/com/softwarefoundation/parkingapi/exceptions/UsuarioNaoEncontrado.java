package com.softwarefoundation.parkingapi.exceptions;

public class UsuarioNaoEncontrado extends RuntimeException {

    public UsuarioNaoEncontrado() {
        super("Usuário não encontrado");
    }
}
