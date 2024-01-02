package com.softwarefoundation.parkingapi.exceptions;

public class UserEntityNotFoundException extends RuntimeException {

    public UserEntityNotFoundException() {
        super("Usuário não encontrado");
    }

    public UserEntityNotFoundException(String message) {
        super(message);
    }
}
