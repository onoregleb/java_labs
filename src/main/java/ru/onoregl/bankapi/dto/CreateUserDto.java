package ru.onoregl.bankapi.dto;

public class CreateUserDto {
    private String firstname;
    private String username;
    private String password;


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}