package ru.onoregl.bankapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onoregl.bankapi.model.User;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> update(User user);
    Optional<User> create(String firstName, String username, String password);
    Optional<User> findById(String id);
    void delete(String id);

}

