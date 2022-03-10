package com.challenges.challenge6.challenge6.services;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import com.challenges.challenge6.challenge6.entities.BankAccountType;
import com.challenges.challenge6.challenge6.entities.Bill;
import com.challenges.challenge6.challenge6.exceptions.InsufficientFundsException;
import com.challenges.challenge6.challenge6.exceptions.InvalidBillIdException;
import com.challenges.challenge6.challenge6.repositories.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;

    public static String INSUFFICIENT_FUNDS_TO_PAY_BILL = "You need to have at least 20% more of the bill in your account to pay it";
    public static String INVALID_BILL_ID = "The id of the bills is not valid";

    public void sufficientFundsToPay (BankAccount account, double toPay){

        if (account.getBalance() < toPay+(account.getBalance()*0.2)){
            throw new InsufficientFundsException(INSUFFICIENT_FUNDS_TO_PAY_BILL);
        }

    }

    public static void validBillId(String billId){

        if (!(billId.startsWith("00") && billId.length() == 7)){
            throw new InvalidBillIdException(INVALID_BILL_ID);
        }

    }

    @Transactional
    public void payBill (BankAccount account, String billId){

        validBillId(billId);

        double toPay = billRepository.getBillById(billId).getToPay();

        if (account.getBankAccountType().equals(BankAccountType.CURRENT)){
            toPay -= toPay*0.1;
        }

        sufficientFundsToPay(account, toPay);

        account.setBalance(account.getBalance()-toPay);

    }

}
