package ru.onoregl.bankapi.dao;

import org.springframework.stereotype.Component;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.model.Card;

import java.util.*;

@Component
public class CardDao {
    static Map<String, Card> repository = new HashMap<>();

    public static void deleteCard(String cardId) {
        if (repository.containsKey(cardId)){
            repository.remove(cardId);
        } else {
            throw new UserNotFoundException("Not found card with id: " + cardId);
        }
    }

    public static Card findById(String cardId) {
        Card card = repository.get(cardId);
        if (card != null) {
            return card;
        }
        throw new RuntimeException();
    }

    public Card create(String AccountId, String userId) {
        Card card = new Card();
        card.setId(UUID.randomUUID().toString());
        card.setAccountid(AccountId);
        card.setUserid(userId);
        repository.put(card.getId(), card);
        return card;
    }

    public List<Card> findCardsByUserId(String userId) {
        List<Card> cardsByUserId = new ArrayList<>();

        for (Card card : repository.values()) {
            if (card.getUserid().equals(userId)) {
                cardsByUserId.add(card);
            }
        }

        if (cardsByUserId.isEmpty()) {
            throw new UserNotFoundException("No cards found for userId: " + userId);
        }

        return cardsByUserId;
    }
}
