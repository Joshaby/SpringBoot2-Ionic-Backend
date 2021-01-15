package com.nelioalves.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errorsList = new ArrayList<>();

    public ValidationError(Integer status, String message, Long time) {
        super(status, message, time);
    }

    public List<FieldMessage> getErrorsList() {
        return errorsList;
    }
    public void addErrors(String field, String message) {
        errorsList.add(new FieldMessage(field, message));
    }
}
