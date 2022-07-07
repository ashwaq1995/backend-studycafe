package com.studyCafeProject.DTO;

//public record LoginDTO(String email , String password){}
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public  class LoginDTO{
    private String username;
    private String password;
}