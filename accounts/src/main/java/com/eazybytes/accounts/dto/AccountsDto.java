package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Accounts",
        description = "schema To hold Account Information")
public class AccountsDto {

    @Schema(description = "Account Number Of A customer in Banck")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number Must be 10 digits")
    @NotEmpty(message = "Account Number can not be empty or Null")
    private Long accountNumber;

    @Schema(description = "Account Type Of a Eazy Bank Account",example = "Saving")
    @NotEmpty(message = "Account Type Cannot be Empty")
    private String accountType;

    @Schema(description = "Branch Address of Bank")
    @NotEmpty(message = "BranchAddress Cannot Be a null or empty")
    private String branchAddress;
}
