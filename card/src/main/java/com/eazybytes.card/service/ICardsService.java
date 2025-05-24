package com.eazybytes.card.service;

import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.entity.Cards;

public interface ICardsService {

    /**
     *
     * @param mobileNumber - mobile Number Of The customer
     */
    void createCards(String mobileNumber);

    /**
     *
     * @param mobileNumber - customer mobileNumber
     * @return - already existing customer under the mobile number
     */

    CardsDto fetchCard(String mobileNumber);

  //  boolean updateCard(CardsDto cardsDto);


}
