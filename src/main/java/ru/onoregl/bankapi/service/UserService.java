package ru.onoregl.bankapi.service;


import ru.onoregl.bankapi.model.User;

public class UserService {

    public User createUser(String name, String password) {

        User client = new User();
        client.setFirstName(name);
        client.setPassword(password);
        return client;
    }

}
