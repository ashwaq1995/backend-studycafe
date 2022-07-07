package com.studyCafeProject.Exception;

public class UserIsAdminException extends RuntimeException {

    public UserIsAdminException(String message) {
        super(message);
    }
}