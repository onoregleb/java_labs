package ru.onoregl.bankapi.service;

import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.dao.CardDao;
import ru.onoregl.bankapi.dto.CreateCardDto;
import ru.onoregl.bankapi.model.Card;

import java.util.List;
import java.util.UUID;

@Service
public class CardService {

    private final CardDao dao;

    public CardService(CardDao dao) {
        this.dao = dao;
    }

    public Card createCard(CreateCardDto createCardDto) {
        Card card = new Card();
        card.setId(UUID.randomUUID().toString());
        card.setUserid(createCardDto.getUserId());
        card.setAccountid(createCardDto.getAccountId());
        dao.create(createCardDto.getAccountId(), createCardDto.getUserId());
        return card;
    }

        public void deleteCard(String CardId){
            try{
                CardDao.deleteCard(CardId);
            } catch (UserNotFoundException e){

            }
    }

    public Card findById(String cardId) {
        Card card = CardDao.findById(cardId);

        if (card == null){
            throw new UserNotFoundException("Account not found for accountId: " + cardId);
        }

        return card;
    }

    public List<Card> findCardsByUserId(String userId) {
        return dao.findCardsByUserId(userId);
    }

}
