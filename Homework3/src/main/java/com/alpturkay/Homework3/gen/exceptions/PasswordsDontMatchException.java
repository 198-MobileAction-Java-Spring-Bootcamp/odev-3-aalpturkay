package com.alpturkay.Homework3.gen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordsDontMatchException extends GenBusinessException{
    public PasswordsDontMatchException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
