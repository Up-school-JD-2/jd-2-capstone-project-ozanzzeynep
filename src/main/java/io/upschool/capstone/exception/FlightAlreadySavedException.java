package io.upschool.capstone.exception;

public class FlightAlreadySavedException extends RuntimeException{
    public FlightAlreadySavedException(String message) {
        super(message);
    }
}
