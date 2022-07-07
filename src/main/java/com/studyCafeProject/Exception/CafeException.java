package com.studyCafeProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CafeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CafeException(String message) {
        super(message);
    }

}
