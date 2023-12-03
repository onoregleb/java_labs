package ru.onoregl.bankapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.onoregl.bankapi.controller.UserNotFoundException;
import ru.onoregl.bankapi.dao.CardDao;
import ru.onoregl.bankapi.dao.UserDao;
import ru.onoregl.bankapi.dto.CreateCardDto;
import ru.onoregl.bankapi.model.Card;

import java.util.List;
import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardDao dao;

    public CardService(CardDao dao) {
        this.dao = dao;
    }

    public Card createCard(CreateCardDto createCardDto) {
        Card card = new Card();
        card.setId(UUID.randomUUID().toString());
        card.setUserid(createCardDto.getUserId());
        card.setAccountid(createCardDto.getAccountId());
        dao.save(card);
        return card;
    }

        public void deleteCard(String CardId){
            try{
                dao.deleteByCardId(CardId);
            } catch (UserNotFoundException e){

            }
    }

    public Card findById(String cardId) {
        Card card = dao.findById(cardId).orElse(null);
        return card;
    }

    public List<Card> findCardsByUserId(String userId) {
        return dao.findByUserid(userId);
    }

}
