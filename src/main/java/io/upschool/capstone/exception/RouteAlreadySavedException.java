package io.upschool.capstone.exception;

public class RouteAlreadySavedException extends RuntimeException{
    public RouteAlreadySavedException(String message) {
        super(message);
    }
}
