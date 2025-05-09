package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Crud REST Api for Accounts in EazyBank",
description = "Crud REST Api in EazyBank to Create ,Update ,Fetch,Delete account Details")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private IAccountService iAccountService;


    @PostMapping(path = "/create")
    @Operation(summary = "Create Account Rest Api",
    description =  "Create API to create a new Customer and Account inside a eazyBank ")
    @ApiResponse(responseCode = "201",description =  "HTTP Status Created")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201));
    }

    // localhost:8080/api/fetch?mobileNumber=123456789+-
    @GetMapping(path = "/fetch")
    @Operation(summary = "Fetch Account Details Rest Api",
            description =  "REST API to fetch Customer & Account details based in mobile Number")
    @ApiResponse(responseCode = "200",description =  "HTTP Status Ok")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number Must be 10 digits")
                                                                 @RequestParam String mobileNumber) {
        CustomerDto customerDto =   iAccountService.fetchAccount(mobileNumber);
        return  ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update Account Details Rest Api",
            description =  "REST API for update customer and Account based on account Number")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
            description = "HTTP Status Ok"),
            @ApiResponse(responseCode = "417",
            description = "Exception Failed"),
            @ApiResponse(responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content =@Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
            ))
    })
    @PutMapping(path = "/update")
    public  ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
       boolean isUpdated =  iAccountService.updateAccount(customerDto);

       if(isUpdated){
           return ResponseEntity.status(HttpStatus.OK)
                   .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
       }else {
           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                   .body(new ResponseDto(AccountsConstants.STATUS_417,AccountsConstants.MESSAGE_417_UPDATE));
       }
    }
}
