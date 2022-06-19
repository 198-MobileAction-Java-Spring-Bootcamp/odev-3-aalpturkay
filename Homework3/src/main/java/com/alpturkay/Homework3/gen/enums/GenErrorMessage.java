package com.alpturkay.Homework3.gen.enums;

import com.alpturkay.Homework3.gen.exceptions.BaseErrorMessage;

public enum GenErrorMessage implements BaseErrorMessage {

    ITEM_NOT_FOUND("Item not found!"),
    ITEM_CONFLICT("Item conflict"),
    USER_ALREADY_EXISTS("User is already exists"),
    PLATE_CANNOT_CONTAIN_TURKISH_LETTERS("Plate cannot contain Turkish Letters"),
    PASSWORDS_DONT_MATCH("Passwords do not match"),
    PARAMETER_CANNOT_BE_NULL("Parameter cannot be null"),
    ;

    private String message;

    GenErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
