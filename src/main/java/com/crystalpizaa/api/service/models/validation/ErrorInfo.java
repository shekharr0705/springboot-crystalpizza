package com.crystalpizaa.api.service.models.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorInfo {

  @Getter
  @Setter
  private List<ValidationInfo> failedValidationFields;

  @Getter
  @Setter
  private String title;

  @Setter
  private String details;

  public String getDetails() {
    if(this.details == null)
      return this.getTitle();
    return this.details;
  }

}
