package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class PriceModel extends OrderItem {


  @Getter
  @Setter
  private double UnitCost;

  private double Total;

  public double getTotal() {
    return  this.getUnitCost() * this.getQuantity();
  }
}
