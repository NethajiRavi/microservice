package com.eazybytes.card.controller;

import com.eazybytes.card.constants.CardsConstants;
import com.eazybytes.card.dto.CardsDto;
import com.eazybytes.card.dto.ResponseDto;
import com.eazybytes.card.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CardsController {

   private final ICardsService iCardService;

   @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber){
       iCardService.createCards(mobileNumber);
       return ResponseEntity
               .status(HttpStatus.CREATED).
               body(new ResponseDto(CardsConstants.MESSAGE_201,CardsConstants.MESSAGE_201));
    }


    @GetMapping(path = "/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber) {
        CardsDto cardsDto = iCardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
   }

   /* @PutMapping(path = "/update")
    public ResponseEntity<CardsDto> updateCardDetails(CardsDto cardsDto) {
        CardsDto cardsDto = iCardService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }*/
}
