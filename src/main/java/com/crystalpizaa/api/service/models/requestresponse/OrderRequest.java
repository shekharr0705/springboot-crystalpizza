package com.crystalpizaa.api.service.models.requestresponse;

import lombok.Getter;
import lombok.Setter;
import com.crystalpizaa.api.service.models.core.Order;

@Getter
@Setter
public class OrderRequest extends Order {

  private int userId;

  private Boolean addressSameAsUserAddress;

  private String address;

}
