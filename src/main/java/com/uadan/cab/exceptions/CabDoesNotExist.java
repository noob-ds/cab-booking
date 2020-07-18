package com.uadan.cab.exceptions;

public class CabDoesNotExist extends RuntimeException {
    public CabDoesNotExist(String message) {
        super(message);
    }
}
