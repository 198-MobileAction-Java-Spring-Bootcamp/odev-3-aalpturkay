package com.alpturkay.Homework3.gen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TurkishLetterException extends GenBusinessException{
    public TurkishLetterException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
