package ru.onoregl.bankapi.dao;

import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserDao {
    static Map<String, User> repository = new HashMap<>();

    public static User findByUsername(String username) {
        for (User user : repository.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void update(User user) {
        if (repository.containsKey(user.getId())) {
            repository.put(user.getId(), user);
        } else {
            throw new UserNotFoundException("User with id + " + user.getId() + "not found.");
        }
    }

    public User create(String firstName, String username, String password) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setPassword(password);
        repository.put(user.getId(), user);
        return user;
    }

    public static User findById(String id) {
        User user = repository.get(id);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User with id + " + id + "not found.");
        }
    }

    public static void deleteUser(String id) {
        if (repository.containsKey(id)) {
            repository.remove(id);
        }
    }
}
