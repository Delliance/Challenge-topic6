package com.challenges.challenge6.challenge6.repositories;

import com.challenges.challenge6.challenge6.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository <Bank, Long> {
}
