package com.studyCafeProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Data
public class LoginForm {

    @NotEmpty(message = "Username is required !")
    private String username;
    @NotEmpty(message = "Password is required !")
    private String password;
}
