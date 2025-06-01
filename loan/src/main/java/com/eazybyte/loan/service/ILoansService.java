package com.eazybyte.loan.service;


import com.eazybyte.loan.dto.LoansDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public interface ILoansService {
    /**
     * @param mobileNumber -- mobile Number of  a customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber -- existing customer Mobile Number
     * @return -- LoanDto Of Existing Customer
     */

    LoansDto fetchLoan(String mobileNumber);


    /**
     * @param loansDto -- update Existing Loan
     * @return -- return true if present
     */
    boolean updateLoan(@Valid LoansDto loansDto);


    /**
     * @param mobileNumber -- delete loan  by a mobile Number of customer
     * @return -- return true if the loan is deleted
     */
    boolean deleteLoan(String mobileNumber);
}
