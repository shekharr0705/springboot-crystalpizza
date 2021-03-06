package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.models.validation.DataNotFoundException;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity<ErrorInfo> exception(ValidationException exception) {
    return new ResponseEntity<>(exception.getErrorInfo(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = DataNotFoundException.class)
  public ResponseEntity<ErrorInfo> exception(DataNotFoundException exception) {
    return new ResponseEntity<>(exception.getErrorInfo(), HttpStatus.NOT_FOUND);
  }

}
