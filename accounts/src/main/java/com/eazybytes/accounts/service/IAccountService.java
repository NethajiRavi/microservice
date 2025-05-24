package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     *
     * @param customerDto -- customer Object
     */

    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber --getby mobile Number
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);
}
