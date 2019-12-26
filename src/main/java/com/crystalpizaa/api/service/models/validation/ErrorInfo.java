package com.crystalpizaa.api.service.models.validation;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class ErrorInfo {

  @Getter
  @Setter
  private List<ValidationInfo> FailedValidationFields;

  @Getter
  @Setter
  private String Title;

  @Getter
  private String Details;

  public String getDetails() {
    if(this.Details == null)
      return this.getTitle();
    return this.Details;
  }

}
