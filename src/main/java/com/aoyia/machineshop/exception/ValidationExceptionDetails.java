package com.aoyia.machineshop.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails{
    /*
        final: Você não pode alterar seu valor após a criação do objeto.
     */
    private final String fields;
    private final String fieldsMessage;
}
