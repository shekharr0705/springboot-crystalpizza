package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class Component {

  @Getter
  @Setter
  private int id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private double price;
}
