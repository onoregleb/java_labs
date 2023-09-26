package ru.onoregl.bankapi.dao;


import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserDao {
    Map<String, User> repository = new HashMap<>();

    public User create(String firstName, String username, String password) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setPassword(password);
        repository.put(user.getId(), user);
        return user;
    }

    public User findById(String id) {
        User user = repository.get(id);
        if (user != null) {
            return user;
        }
        throw new RuntimeException();
    }

}
}
