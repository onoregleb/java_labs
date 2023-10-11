package ru.onoregl.bankapi.service;

import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateUserDto;
import ru.onoregl.bankapi.model.User;

@Service
public class UserService {

    public User createUser(CreateUserDto createUserDto) {
        User client = new User();
        client.setUsername(createUserDto.getUsername());
        client.setPassword(createUserDto.getPassword());
        return client;
    }

    public void deleteUser(String userId){
        try{
            UserDao.deleteUser(userId);
        } catch (UserNotFoundException e){

        }
    }

//    public User createUser(String name, String password) {
//        User client = new User();
//        client.setFirstName(name);
//        client.setPassword(password);
//        return client;
//    }

}
