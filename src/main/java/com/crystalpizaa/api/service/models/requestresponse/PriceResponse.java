package com.crystalpizaa.api.service.models.requestresponse;

import com.crystalpizaa.api.service.models.core.PriceModel;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceResponse {

  private double total;

  @Getter
  @Setter
  private List<PriceModel> pizzas;

  @Getter
  @Setter
  private List<PriceModel> addOns;

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
