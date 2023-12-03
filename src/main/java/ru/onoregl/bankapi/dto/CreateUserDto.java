package ru.onoregl.bankapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserDto {
    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}