package com.pdvsystem.PdvSystemApplication.exceptions;


import javax.servlet.ServletException;

public class OutOfAreaException extends ServletException {

    public OutOfAreaException(final String message) {
        super(message);
    }

}
