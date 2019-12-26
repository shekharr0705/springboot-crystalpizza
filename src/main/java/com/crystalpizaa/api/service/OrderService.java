package com.crystalpizaa.api.service;

import com.crystalpizaa.api.service.models.requestresponse.OrderRequest;
import com.crystalpizaa.api.service.models.requestresponse.OrderResponse;
import com.crystalpizaa.api.service.models.requestresponse.PriceRequest;
import com.crystalpizaa.api.service.models.requestresponse.PriceResponse;
import java.util.List;

public interface OrderService {

  PriceResponse getPrice(PriceRequest request);

  OrderResponse placeOrder(OrderRequest request);

  List<OrderResponse> getAll();

  OrderResponse getById(int id);

  Boolean cancelOrder(int orderId);

}
