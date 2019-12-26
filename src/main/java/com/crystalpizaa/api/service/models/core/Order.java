package com.crystalpizaa.api.service.models.core;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Order {

  @Getter
  @Setter
  private List<OrderItem> pizzas;

  @Getter
  @Setter
  private List<OrderItem> addOns;
}
