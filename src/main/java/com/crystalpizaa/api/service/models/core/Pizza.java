package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class Pizza extends Component {

  @Getter
  @Setter
  private PizzaType Type;

  @Getter
  @Setter
  private Size Size;
}