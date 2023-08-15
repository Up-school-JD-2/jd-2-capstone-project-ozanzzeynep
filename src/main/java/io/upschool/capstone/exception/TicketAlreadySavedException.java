package io.upschool.capstone.exception;

public class TicketAlreadySavedException extends RuntimeException{

    public TicketAlreadySavedException(String message) {
        super(message);
    }
}
