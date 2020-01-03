package com.crystalpizaa.api.service.models.validation;

import lombok.Getter;

public class DataNotFoundException extends RuntimeException {
  @Getter
  ErrorInfo errorInfo;

  public DataNotFoundException(ErrorInfo errorInfo) {

    this.errorInfo = errorInfo;

  }
}
