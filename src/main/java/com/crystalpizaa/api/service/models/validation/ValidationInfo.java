package com.crystalpizaa.api.service.models.validation;

import lombok.Getter;


public class ValidationInfo{

  @Getter
  private String fieldName;

  @Getter
  private String info;

  public  ValidationInfo(String fieldName, String info) {
    this.fieldName = fieldName;
    this.info = info;
  }

}
