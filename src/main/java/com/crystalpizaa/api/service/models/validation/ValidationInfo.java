package com.crystalpizaa.api.service.models.validation;

import lombok.Getter;


public class ValidationInfo{

  @Getter
  private String FieldName;

  @Getter
  private String Info;

  public  ValidationInfo(String fieldName, String info) {
    this.FieldName = fieldName;
    this.Info = info;
  }

}
