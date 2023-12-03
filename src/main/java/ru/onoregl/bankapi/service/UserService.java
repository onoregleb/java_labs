package ru.onoregl.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateUserDto;
import ru.onoregl.bankapi.model.User;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public User createUser(CreateUserDto createUserDto) {
        User user = new User();
        //user.setId(UUID.randomUUID().toString());
        user.setUsername(createUserDto.getUsername());
        user.setFirstName(createUserDto.getFirstname());
        user.setPassword(createUserDto.getPassword());
        dao.save(user);
        return user;
    }

    public void deleteUser(String userId){
        dao.deleteById(userId);
    }

    public User updatePassword(String username, String newPassword) {
        User user = dao.findByUsername(username).orElse(null);
        if (user != null) {
            user.setPassword(newPassword);
            dao.save(user);
        }
        return user;
    }
}
