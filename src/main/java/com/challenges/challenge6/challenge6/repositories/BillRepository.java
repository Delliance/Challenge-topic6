package com.challenges.challenge6.challenge6.repositories;

import com.challenges.challenge6.challenge6.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository <Bill, Long> {

    Bill getBillById (long id);



}
