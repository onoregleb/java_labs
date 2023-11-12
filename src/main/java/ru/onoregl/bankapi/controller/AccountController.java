package ru.onoregl.bankapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onoregl.bankapi.dto.CreateAccountDto;
import ru.onoregl.bankapi.model.Account;
import ru.onoregl.bankapi.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody CreateAccountDto requestBody) {
        Account user = accountService.createAccountForUser(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(path = "/{accountId}", produces = "application/json")
    public ResponseEntity<Account> findById(@PathVariable(value = "accountId") String accountId) {
        try {
            return new ResponseEntity<>(accountService.findById(accountId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/user/{userId}", produces = "application/json")
    public ResponseEntity<List<Account>> findByUserId(@PathVariable(value = "userId") String userId) {
        List<Account> userAccounts = accountService.findByUserId(userId);

        if(userAccounts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userAccounts);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{accountId}/change-balance")
    public ResponseEntity<Account> changeBalance(
            @PathVariable(value = "accountId") String accountId,
            @RequestParam("amount") double amount) {
        Account updatedAccount = accountService.changeAccountBalance(accountId, amount);
        {
            if (updatedAccount != null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedAccount);
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }
}
