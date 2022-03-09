package com.challenges.challenge6.challenge6.services;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import com.challenges.challenge6.challenge6.exceptions.InsufficientFundsException;
import com.challenges.challenge6.challenge6.repositories.BankAccountRepository;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql ("/bank.sql")
@Sql ("/tbl_account.sql")
class BankAccountServiceTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountService service;

    @Test
    public void invalidTransactionNotEnoughFunds (){

//        Here I just chose a random account, info: 15,363284,SAVINGS,509-01-2100,Dennet,Edgett,9
        BankAccount originAccount = bankAccountRepository.getBankAccountById(15);

//        Here I just chose a random account, info: 20,2930302,SAVINGS,593-39-1199,Andros,Jadczak,5 <- 5 = Globant
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(20);

        long transferValue = 3_000_000;

        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () ->
                service.enoughTransferFunds(originAccount.getBalance(), transferValue));

        assertEquals(service.INSUFFICIENT_FUNDS_TO_MAKE_TRANSFERENCE_MESSAGE, exception.getMessage());

    }

    @Test
    public void validTransactionEnoughFundsSameBank(){

//        15,363284,SAVINGS,509-01-2100,Dennet,Edgett,9
        BankAccount originAccount = bankAccountRepository.getBankAccountById(15);
//        75,2545474,SAVINGS,456-43-6610,Esme,Hawtrey,9
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(75);

        long transferValue = 300_000;

        System.out.println("originAccount = " + originAccount);
        System.out.println("destinyAccount = " + destinyAccount);

        service.transferFundsToOtherAccount(originAccount, destinyAccount, transferValue);

        System.out.println("Origin account new balance: "+originAccount.getBalance());
        System.out.println("Destiny account new balance: "+destinyAccount.getBalance());

    }

    @Test
    public void validTransactionEnoughFundsDifferentBank(){

//        15,363284,SAVINGS,509-01-2100,Dennet,Edgett,9
        BankAccount originAccount = bankAccountRepository.getBankAccountById(15);
//        20,2930302,SAVINGS,593-39-1199,Andros,Jadczak,5 <- 5 = Globant
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(20);

        long transferValue = 300_000;

        System.out.println("originAccount = " + originAccount);
        System.out.println("destinyAccount = " + destinyAccount);

        service.transferFundsToOtherAccount(originAccount, destinyAccount, transferValue);

        System.out.println("Origin account new balance: "+originAccount.getBalance());
        System.out.println("Destiny account new balance: "+destinyAccount.getBalance());

    }

}