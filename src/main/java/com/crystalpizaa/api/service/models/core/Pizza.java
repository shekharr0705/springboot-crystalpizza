package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class Pizza extends Component {

  @Getter
  @Setter
  private PizzaType type;

  @Getter
  @Setter
  private Size size;
}