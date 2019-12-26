package com.crystalpizaa.api.service.models.validation;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


public class ValidationException extends  RuntimeException{

  @Getter
  ErrorInfo errorInfo;

  public ValidationException(ErrorInfo errorInfo) {

    this.errorInfo = errorInfo;

  }
}
