package com.eazybytes.card.service.impl;

import com.eazybytes.card.constants.CardsConstants;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entity.Cards;
import com.eazybytes.card.exception.CardAlreadyExistsException;
import com.eazybytes.card.exception.ResourceNotFoundException;
import com.eazybytes.card.mapper.CardsMapper;
import com.eazybytes.card.repository.CardsRepository;
import com.eazybytes.card.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class ICardServiceImpl implements ICardsService {

    private  CardsRepository cardsRepository;

    @Override
    public void createCards(String mobileNumber) {
        Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent()){
            throw new CardAlreadyExistsException("Card Already Registered With give Mobile Number" + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).
                orElseThrow(()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    /**
     * @param cardsDto -- cards Dto
     * @return -- indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    /**
     * @param mobileNumber -- mobile number of customer
     * @return -- indicating if the deleted of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return  true;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(100000);
        return newCard;
    }
}
