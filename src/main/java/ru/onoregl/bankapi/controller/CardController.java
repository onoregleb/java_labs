package ru.onoregl.bankapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.onoregl.bankapi.dto.CreateCardDto;
import ru.onoregl.bankapi.model.Card;
import ru.onoregl.bankapi.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody CreateCardDto requestBody) {
        Card card = cardService.createCard(requestBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(card);
    }

    @GetMapping(path = "/{isd}", produces = "application/json")
    public ResponseEntity<Card> findById(@PathVariable(value = "id") String cardId) {
        try {
            return new ResponseEntity<>(cardService.findById(cardId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable String cardId) {
        try {
            cardService.deleteCard(cardId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/users/{userId}", produces = "application/json")
    public ResponseEntity<List<Card>> findCardsByUserId(@PathVariable(value = "userId") String userId) {
        List<Card> cards = cardService.findCardsByUserId(userId);

        if (cards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cards, HttpStatus.OK);
        }
    }

}
