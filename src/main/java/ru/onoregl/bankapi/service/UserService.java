package ru.onoregl.bankapi.service;

import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateUserDto;
import ru.onoregl.bankapi.model.User;

import java.util.UUID;

@Service
public class UserService {

    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public User createUser(CreateUserDto createUserDto) {
        User client = new User();
        client.setId(UUID.randomUUID().toString());
        client.setUsername(createUserDto.getUsername());
        client.setFirstName(createUserDto.getFirstname());
        client.setPassword(createUserDto.getPassword());
        dao.create(createUserDto.getFirstname(), createUserDto.getUsername(), createUserDto.getPassword());
        return client;
    }

    public void deleteUser(String userId){
        try{
            UserDao.deleteUser(userId);
        } catch (UserNotFoundException e){

        }
    }

    public User updatePassword(String username, String newPassword) {
        try {
            User user = dao.findByUsername(username);
            if (user != null) {
                user.setPassword(newPassword);
                UserDao.update(user);
                return user;
            } else {
                return null;
            }
        } catch (UserNotFoundException e) {
            return null;
        }
    }
}
