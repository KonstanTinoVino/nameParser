package com.elsevier.test.exceptions;

public class TooFewNamesException extends Exception{

    public TooFewNamesException(String errorMessage) {
        super(errorMessage);
    }
}
