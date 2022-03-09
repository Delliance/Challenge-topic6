package com.challenges.challenge6.challenge6.exceptions;

public class InvalidTargetFundsException extends RuntimeException{

//    This exception is thrown when you want to transfer money to a CURRENT account and that account has 3 times the
//    transfer as funds

    public InvalidTargetFundsException(String message) {
        super(message);
    }
}
