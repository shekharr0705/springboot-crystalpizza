package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class AddOn extends Component {

  @Getter
  @Setter
  private AddOnType type;

  @Getter
  @Setter
  private Size size;
}