package com.alpturkay.Homework3.gen.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenBusinessException extends RuntimeException{
    private final BaseErrorMessage baseErrorMessage;
}
