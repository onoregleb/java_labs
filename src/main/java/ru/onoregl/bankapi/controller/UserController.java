package ru.onoregl.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
            return new ResponseEntity<>(service.createUser(requestBody), HttpStatus.CREATED);
        }
        @GetMapping(path = "/{isd}", produces = "application/json")
        public ResponseEntity<User> findById(@PathVariable(value = "id") String id) {
            try {
                return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
            } catch (NotFoundException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    }
}
