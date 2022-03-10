package com.challenges.challenge6.challenge6.services;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import com.challenges.challenge6.challenge6.exceptions.InsufficientFundsException;
import com.challenges.challenge6.challenge6.exceptions.InvalidTargetFundsException;
import com.challenges.challenge6.challenge6.repositories.BankAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
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

//        20,2930302,SAVINGS,593-39-1199,Andros,Jadczak,5 <- 5 = Globant
        BankAccount originAccount = bankAccountRepository.getBankAccountById(20);
//        26,2320615,CURRENT,726-32-3115,Ilysa,Petto,5 <- 5 = Globant
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(26);

        double transferValue = 3_000_000;

        InsufficientFundsException exception = Assertions.assertThrows(InsufficientFundsException.class, () ->
                service.transferFundsToOtherAccount(originAccount,destinyAccount,transferValue));

        assertEquals(service.INSUFFICIENT_FUNDS_TO_MAKE_TRANSFERENCE_MESSAGE, exception.getMessage());

    }

    @Test
    public void validTransactionEnoughFundsSameBank(){

//        15,363284,SAVINGS,509-01-2100,Dennet,Edgett,9
        BankAccount originAccount = bankAccountRepository.getBankAccountById(15);
//        75,2545474,SAVINGS,456-43-6610,Esme,Hawtrey,9
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(75);

        double transferValue = 300_000;

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

        double transferValue = 300_000;

        System.out.println("originAccount = " + originAccount);
        System.out.println("destinyAccount = " + destinyAccount);
//        The origin account will have 303_500 less, since the tax for different bank was applied
        service.transferFundsToOtherAccount(originAccount, destinyAccount, transferValue);

        System.out.println("Origin account new balance: "+originAccount.getBalance());
        System.out.println("Destiny account new balance: "+destinyAccount.getBalance());

    }

    @Test
    public void invalidTransactionDestinyAccountCurrentHasTripleTransfer(){

//        20,2930302,SAVINGS,593-39-1199,Andros,Jadczak,5 <- 5 = Globant
        BankAccount originAccount = bankAccountRepository.getBankAccountById(20);
//        26,2320615,CURRENT,726-32-3115,Ilysa,Petto,5 <- 5 = Globant
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(26);

        double transferValue = 2_000_000;

        InvalidTargetFundsException exception = assertThrows(InvalidTargetFundsException.class, () ->
                service.transferFundsToOtherAccount(originAccount,destinyAccount,transferValue));

        assertEquals(BankAccountService.TARGET_CURRENT_ACCOUNT_HAS_MORE_THAN_TRIPLE_TRANSFERENCE_MESSAGE, exception.getMessage());

    }

    @Test
    public void validTransactionEnoughFundsGreaterThan1500000SameBank(){


//        20,2930302,SAVINGS,593-39-1199,Andros,Jadczak,5 <- 5 = Globant
        BankAccount originAccount = bankAccountRepository.getBankAccountById(20);
//        34,2194429,SAVINGS,248-51-8592,Bibbie,Cockett,5
        BankAccount destinyAccount = bankAccountRepository.getBankAccountById(34);

        double transferValue = 2_000_000;

        System.out.println("originAccount = " + originAccount);
        System.out.println("destinyAccount = " + destinyAccount);
//        The origin account will have 303_500 less, since the tax for different bank was applied
        service.transferFundsToOtherAccount(originAccount, destinyAccount, transferValue);

        System.out.println("Origin account new balance: "+originAccount.getBalance());
        System.out.println("Destiny account new balance: "+destinyAccount.getBalance());

    }

}