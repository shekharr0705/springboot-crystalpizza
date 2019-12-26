package com.crystalpizaa.api.service.models.requestresponse;

import com.crystalpizaa.api.service.models.core.PriceModel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import com.crystalpizaa.api.service.models.core.Order;

@Getter
@Setter
public class PriceResponse {

  private double Total;

  @Getter
  @Setter
  private List<PriceModel> Pizzas;

  @Getter
  @Setter
  private List<PriceModel> AddOns;

  public double getTotal() {
    double total = 0;

    for (PriceModel p : this.getPizzas()) {
      total += p.getTotal();
    }

    for (PriceModel p : this.getAddOns()) {
      total += p.getTotal();
    }
    return total;
  }

}
