package com.challenges.challenge6.challenge6.services;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import com.challenges.challenge6.challenge6.entities.Bill;
import com.challenges.challenge6.challenge6.exceptions.InsufficientFundsException;
import com.challenges.challenge6.challenge6.exceptions.InvalidBillIdException;
import com.challenges.challenge6.challenge6.exceptions.InvalidTargetFundsException;
import com.challenges.challenge6.challenge6.repositories.BankAccountRepository;
import com.challenges.challenge6.challenge6.repositories.BillRepository;
import com.challenges.challenge6.challenge6.repositories.BusinessRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Sql ("/bank.sql")
@Sql ("/tbl_account.sql")
@Sql ("/business.sql")
class BillServiceTest {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BillService billService;

    @Autowired
    BusinessRepository businessRepository;

    @BeforeEach
    public void createBill() {

        Bill firstBill = Bill.builder()
                .id("0012345")
                .dueDate(LocalDateTime.now())
                .toPay(200_000)
                .business(businessRepository.getById(1L))
                .build();

        billRepository.save(firstBill);

        Bill secondBill = Bill.builder()
                .id("0045678")
                .dueDate(LocalDateTime.now())
                .toPay(1_800_000)
                .business(businessRepository.getById(7L))
                .build();


        billRepository.save(firstBill);
        billRepository.save(secondBill);

    }

    @Test
    public void validTransactionEnoughFunds (){

//        1,1811892,SAVINGS,398-60-5308,Mayor,Mawditt,10
        BankAccount bankAccount = bankAccountRepository.getBankAccountById(1);
        Bill bill = billRepository.getBillById("0012345");

        System.out.println("bankAccount before pay= " + bankAccount);
        System.out.println("bill = " + bill);

        billService.payBill(bankAccount,bill.getId());

        System.out.println("bankAccount after pay= " + bankAccount);

    }

    @Test
    public void invalidTransactionNotEnoughFunds (){

//        1,1811892,SAVINGS,398-60-5308,Mayor,Mawditt,10
        BankAccount bankAccount = bankAccountRepository.getBankAccountById(1);
        Bill bill = billRepository.getBillById("0045678");

        System.out.println("bankAccount before pay= " + bankAccount);
        System.out.println("bill = " + bill);

        InsufficientFundsException exception = Assertions.assertThrows(InsufficientFundsException.class, () ->
                billService.payBill(bankAccount,bill.getId()));

        assertEquals(BillService.INSUFFICIENT_FUNDS_TO_PAY_BILL, exception.getMessage());

    }

    @Test
    public  void invalidBillId (){
        BankAccount bankAccount = bankAccountRepository.getBankAccountById(1);
        String billId = "45678";

        System.out.println("bankAccount before pay= " + bankAccount);
        System.out.println("bill = " + billId);

        InvalidBillIdException exception = assertThrows( InvalidBillIdException.class, () ->
                billService.payBill(bankAccount,billId));

        assertEquals(BillService.INVALID_BILL_ID,exception.getMessage());
    }


}