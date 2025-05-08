package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private IAccountService iAccountService;


    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201));
    }

    // localhost:8080/api/fetch?mobileNumber=123456789+-
    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number Must be 10 digits")
                                                                 @RequestParam String mobileNumber) {
        CustomerDto customerDto =   iAccountService.fetchAccount(mobileNumber);
        return  ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping(path = "/update")
    public  ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
       boolean isUpdated =  iAccountService.updateAccount(customerDto);

       if(isUpdated){
           return ResponseEntity.status(HttpStatus.OK)
                   .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
       }else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
       }
    }
}
