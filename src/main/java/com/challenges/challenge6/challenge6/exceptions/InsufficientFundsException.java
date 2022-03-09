package com.challenges.challenge6.challenge6.exceptions;

public class InsufficientFundsException extends RuntimeException{

//    This exception is thrown if you don't have enough founds to make a transference
//    It will also be thrown when you don't have enough founds to pay a bill

    public InsufficientFundsException(String message) {
        super(message);
    }
}
