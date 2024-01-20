package com.mazloom.exception;

import com.mazloom.message.ErrorMessage;
import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ProjectRuntimeException {

    @Override
    protected HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public static AccessDeniedException getInstance() {
        return new AccessDeniedException(ErrorMessage.accessDenied());
    }

    public static AccessDeniedException getInstance(String msg) {
        return new AccessDeniedException(msg);
    }
}