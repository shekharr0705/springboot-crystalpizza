package com.crystalpizaa.api.service.models.requestresponse;

import com.crystalpizaa.api.service.models.core.PriceModel;
import com.crystalpizaa.api.service.models.core.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

  private double total;

  private Integer orderId;

  private User customerDetails;

  private String deliveryAddress;

  private List<PriceModel> pizzas;

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

  private LocalDateTime orderDate;

}
