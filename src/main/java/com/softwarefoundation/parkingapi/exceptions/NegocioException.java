package com.softwarefoundation.parkingapi.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NegocioException extends RuntimeException {

    private HttpStatus httpStatus;

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
