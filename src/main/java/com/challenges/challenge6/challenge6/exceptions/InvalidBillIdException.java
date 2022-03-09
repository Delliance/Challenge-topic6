package com.challenges.challenge6.challenge6.exceptions;

public class InvalidBillIdException extends RuntimeException{

//    This exception is thrown when the bill's id does not have two zeroes at the start and is not 7 digit long

    public InvalidBillIdException(String message) {
        super(message);
    }
}
