package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number Must be 10 digits")
    @NotEmpty(message = "Account Number can not be empty or Null")
    private Long accountNumber;
    @NotEmpty(message = "Account Type Cannot be Empty")
    private String accountType;
    @NotEmpty(message = "BranchAddress Cannot Be a null or empty")
    private String branchAddress;
}
