package com.challenges.challenge6.challenge6.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql ("/bank.sql")
@Sql ("/tbl_account.sql")
@Sql ("/business.sql")
class BillServiceTest {

    @Autowired
    private BillService billService;

    @Test
    public void test(){

    }

}