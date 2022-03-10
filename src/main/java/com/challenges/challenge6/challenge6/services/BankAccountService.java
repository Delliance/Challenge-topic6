package com.challenges.challenge6.challenge6.services;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import com.challenges.challenge6.challenge6.entities.BankAccountType;
import com.challenges.challenge6.challenge6.exceptions.InsufficientFundsException;
import com.challenges.challenge6.challenge6.exceptions.InvalidTargetFundsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BankAccountService {

    public static String INSUFFICIENT_FUNDS_TO_MAKE_TRANSFERENCE_MESSAGE = "Not enough funds to make the transference";
    public static String TARGET_CURRENT_ACCOUNT_HAS_MORE_THAN_TRIPLE_TRANSFERENCE_MESSAGE = "Destiny account has triple or more than transfer";

    public void enoughTransferFunds (double funds, double transfer){

        if (funds < transfer){
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS_TO_MAKE_TRANSFERENCE_MESSAGE);
        }

    }

    public void destinyAccountCurrentLessThanTriple (BankAccount destiny, double transfer){

        if (destiny.getBankAccountType().equals(BankAccountType.CURRENT) && destiny.getBalance() < 3*transfer){
            throw new InvalidTargetFundsException(TARGET_CURRENT_ACCOUNT_HAS_MORE_THAN_TRIPLE_TRANSFERENCE_MESSAGE);
        }

    }

    @Transactional
    public void transferFundsToOtherAccount (BankAccount originAccount, BankAccount destinyAccount, double transfer){

        double largeTransferTax = 0;
//        Check if transfer greater than 1_500_000 to apply 3% tax
        if (transfer > 1_500_000){
            largeTransferTax = transfer*0.03;
        }

//        Check if different banks to apply 3500 tax
        double differentBankTax = 0;

        if (originAccount.getBank().getId() != destinyAccount.getBank().getId()){
            transfer += 3500;
            differentBankTax = 3500;
        }

//        Check if enough balance
        enoughTransferFunds(originAccount.getBalance(), transfer);

//        Check if account is current to not have more than triple transference
        destinyAccountCurrentLessThanTriple(destinyAccount, transfer);

        originAccount.setBalance(originAccount.getBalance()-transfer);

        destinyAccount.setBalance(destinyAccount.getBalance()+transfer-differentBankTax-largeTransferTax);

    }

}
