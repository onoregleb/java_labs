package ru.onoregl.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateUserDto;
import ru.onoregl.bankapi.model.Card;
import ru.onoregl.bankapi.model.User;
import ru.onoregl.bankapi.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService service;
    private UserDao dao;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto requestBody) {
        User user = service.createUser(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity findById(@PathVariable(value = "id") String id) {
        try {
            return new ResponseEntity<>(dao.findById(id), HttpStatus.OK);
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

    @PutMapping(path = "/{username}/password")
    public ResponseEntity<User> updatePassword(
            @PathVariable(value = "username") String username,
            @RequestBody Map<String, String> request
    ) {
        String newPassword = request.get("newPassword");
        User updatedUser = service.updatePassword(username, newPassword);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

