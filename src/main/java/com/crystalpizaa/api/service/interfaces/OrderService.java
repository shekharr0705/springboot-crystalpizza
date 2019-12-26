package com.crystalpizaa.api.service.interfaces;

import com.crystalpizaa.api.service.models.requestresponse.OrderRequest;
import com.crystalpizaa.api.service.models.requestresponse.OrderResponse;
import com.crystalpizaa.api.service.models.requestresponse.PriceRequest;
import com.crystalpizaa.api.service.models.requestresponse.PriceResponse;
import java.util.List;

public interface OrderService {

  PriceResponse GetPrice(PriceRequest request);

  OrderResponse PlaceOrder(OrderRequest request);

  List<OrderResponse> GetAll();

  OrderResponse GetById(int id);

  Boolean CancelOrder(int orderId);

}
