package com.alpturkay.Homework3.gen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemConflictException extends GenBusinessException{
    public ItemConflictException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
