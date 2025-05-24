package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",
description = "schema To hold customer And Account")
public class CustomerDto {

    @Schema(description = "Name of the Customer ",example = "Surya")
    @NotEmpty(message = "Name Can not be empty or Null")
    @Size(min = 5,max = 10,message = "The length of customer Name should be between 5 to 10")
    private String name;

    @Schema(description = "Email of the Customer ",example = "asc@gmail.com")
    @NotEmpty(message = "Email Address can not be Null or Empty")
    @Email(message = "Email Address should be a correct Value")
    private String email;

    @Schema(description = "Mobile Number of the Customer ",example = "1234567890")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number Must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account Details of the Customer")
    private AccountsDto accountsDto;
}
