package com.crystalpizaa.api.service.models.core;

import lombok.Getter;
import lombok.Setter;

public class PriceModel extends OrderItem {


  @Getter
  @Setter
  private double unitCost;

  private double total;

  public double getTotal() {
    return  this.getUnitCost() * this.getQuantity();
  }
}
