package com.bitsandbytes.product.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RoleBasedAuthorizationException extends RuntimeException {
    public RoleBasedAuthorizationException(String message) {
        super(message);
    }
}