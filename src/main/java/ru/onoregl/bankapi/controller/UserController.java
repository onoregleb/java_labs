package ru.onoregl.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateUserDto;
import ru.onoregl.bankapi.model.User;
import ru.onoregl.bankapi.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto requestBody) {
        User user = service.createUser(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(path = "/{isd}", produces = "application/json")
    public ResponseEntity findById(@PathVariable(value = "id") String id) {
        try {
            return new ResponseEntity<>(UserDao.findById(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            service.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

