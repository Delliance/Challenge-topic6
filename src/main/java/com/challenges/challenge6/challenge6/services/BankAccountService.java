package com.challenges.challenge6.challenge6.services;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import com.challenges.challenge6.challenge6.exceptions.InsufficientFundsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BankAccountService {

    public String INSUFFICIENT_FUNDS_TO_MAKE_TRANSFERENCE_MESSAGE ="Not enough funds to make the transference";

    public Long enoughTransferFunds (Long funds, Long transfer){

        if (funds < transfer){
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS_TO_MAKE_TRANSFERENCE_MESSAGE);
        }

        return funds;
    }

    @Transactional
    public void transferFundsToOtherAccount (BankAccount originAccount, BankAccount destinyAccount, Long transfer){

        enoughTransferFunds(originAccount.getBalance(), transfer);

        long transferTax = 0;

        if (originAccount.getBank().getId() != destinyAccount.getBank().getId()){
            transferTax = 3500;
        }

        originAccount.setBalance(originAccount.getBalance()-transfer-transferTax);

        destinyAccount.setBalance(destinyAccount.getBalance()+transfer);

    }

}
