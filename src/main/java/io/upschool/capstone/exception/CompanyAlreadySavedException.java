package io.upschool.capstone.exception;

public class CompanyAlreadySavedException extends RuntimeException{
    public CompanyAlreadySavedException(String message) {
        super(message);
    }
}
