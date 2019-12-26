package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class Component {

  @Getter
  @Setter
  private int Id;

  @Getter
  @Setter
  private String Name;

  @Getter
  @Setter
  private String Description;

  @Getter
  @Setter
  private double Price;
}
