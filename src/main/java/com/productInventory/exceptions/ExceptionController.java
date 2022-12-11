package com.productInventory.exceptions;

import static com.productInventory.constants.StringConstants.BAD_DATA;
import static com.productInventory.constants.StringConstants.NOT_FOUND;
import static com.productInventory.constants.StringConstants.SERVER_ERROR;

import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
  @ExceptionHandler(ResourceNotFound.class)
  protected ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFound exception) {
    ExceptionResponse response =
        new ExceptionResponse(NOT_FOUND, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ServiceUnavailable.class)
  protected ResponseEntity<ExceptionResponse> serverError(ServiceUnavailable exception) {
    ExceptionResponse response =
        new ExceptionResponse(SERVER_ERROR, new Date(), exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(DataIntegrityViolation.class)
  protected ResponseEntity<ExceptionResponse> uniqueError(DataIntegrityViolation exception) {
    ExceptionResponse response = new ExceptionResponse(BAD_DATA, new Date(),
        exception.getMessage());

    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }


  private String parseMessage(MethodArgumentNotValidException ex) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    StringBuilder message = new StringBuilder();
    for (FieldError err : errors) {
      message.append(err.getDefaultMessage());
      message.append(" ");
    }
    return message.toString().trim();
  }
}
