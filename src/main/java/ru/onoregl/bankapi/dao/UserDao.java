package ru.onoregl.bankapi.dao;

import ru.onoregl.bankapi.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UserDao {
    static Map<String, User> repository = new HashMap<>();

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
        }
        throw new RuntimeException();
    }

    public static void deleteUser(String id){
        if (repository.containsKey(id)){
            repository.remove(id);
        }
    }

}

