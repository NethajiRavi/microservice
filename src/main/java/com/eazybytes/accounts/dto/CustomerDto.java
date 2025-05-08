package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    @NotEmpty(message = "Name Can not be empty or Null")
    @Size(min = 5,max = 10,message = "The length of customer Name should be between 5 to 10")
    private String name;
    @NotEmpty(message = "Email Address can not be Null or Empty")
    @Email(message = "Email Address should be a correct Value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number Must be 10 digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
