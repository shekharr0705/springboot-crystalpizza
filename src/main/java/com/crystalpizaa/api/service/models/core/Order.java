package com.crystalpizaa.api.service.models.core;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Order {

  @Getter
  @Setter
  private List<OrderItem> Pizzas;

  @Getter
  @Setter
  private List<OrderItem> AddOns;
}
