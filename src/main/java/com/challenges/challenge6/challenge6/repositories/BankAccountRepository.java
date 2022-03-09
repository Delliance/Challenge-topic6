package com.challenges.challenge6.challenge6.repositories;

import com.challenges.challenge6.challenge6.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository <BankAccount, Long> {

    BankAccount getBankAccountById(long id);

    List<BankAccount> findBankAccountByOwnerFirstName(String ownerName);



}
