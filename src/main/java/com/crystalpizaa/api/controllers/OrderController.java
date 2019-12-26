package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crystalpizaa.api.service.models.requestresponse.OrderRequest;
import com.crystalpizaa.api.service.models.requestresponse.OrderResponse;
import com.crystalpizaa.api.service.models.requestresponse.PriceRequest;
import com.crystalpizaa.api.service.models.requestresponse.PriceResponse;

@RestController
@RequestMapping(name = "order", value = "/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @RequestMapping(name = "Get Price", value = "/get-price", method = RequestMethod.POST)
  public ResponseEntity<PriceResponse> getPrice(@RequestBody PriceRequest request) {
    return new ResponseEntity<>(this.orderService.getPrice(request), HttpStatus.OK) ;
  }

  @RequestMapping(name = "Place Order", value = "/place",method = RequestMethod.POST)
  public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
    return new ResponseEntity<>(this.orderService.placeOrder(request),HttpStatus.CREATED);
  }

  @RequestMapping(name = "Cancel Order", value = "/cancel/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> cancelOrder(@PathVariable int id) {
    return new ResponseEntity<>(this.orderService.cancelOrder(id),HttpStatus.OK);
  }

  @RequestMapping(name = "Get All", value = "/get/all",method = RequestMethod.GET)
  public ResponseEntity<List<OrderResponse>> getAll() {
    return new ResponseEntity<>(this.orderService.getAll(),HttpStatus.OK);
  }

  @RequestMapping(name = "Get By Id", value = "/get/{id}",method = RequestMethod.GET)
  public ResponseEntity<OrderResponse> getById(@PathVariable int id) {
    return new ResponseEntity<>(this.orderService.getById(id),HttpStatus.OK);
  }
}
