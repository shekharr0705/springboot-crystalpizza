package com.crystalpizaa.api.service.models.validation;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class ErrorInfo {

  @Getter
  @Setter
  private List<ValidationInfo> failedValidationFields;

  @Getter
  @Setter
  private String title;

  @Getter
  private String details;

  public String getDetails() {
    if(this.details == null)
      return this.getTitle();
    return this.details;
  }

}
