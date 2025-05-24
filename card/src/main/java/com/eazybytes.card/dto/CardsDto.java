package com.eazybytes.card.dto;


import lombok.Data;

@Data
public class CardsDto {

    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int totalPaid;
    private int availableLimit;
    private int amountUsed;



}
