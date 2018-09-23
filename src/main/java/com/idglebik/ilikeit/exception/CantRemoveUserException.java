package com.idglebik.ilikeit.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CantRemoveUserException extends RuntimeException {

    public CantRemoveUserException(String exception) {
        super(exception);
    }

}