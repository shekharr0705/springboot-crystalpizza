package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.interfaces.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<PriceResponse> GetPrice(@RequestBody PriceRequest request) {
    return new ResponseEntity<>(this.orderService.GetPrice(request), HttpStatus.OK) ;
  }

  @RequestMapping(name = "Place Order", value = "/place",method = RequestMethod.POST)
  public ResponseEntity<OrderResponse> PlaceOrder(@RequestBody OrderRequest request) {
    return new ResponseEntity<>(this.orderService.PlaceOrder(request),HttpStatus.CREATED);
  }

  @RequestMapping(name = "Cancel Order", value = "/cancel/{id}",method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> CancelOrder(@PathVariable int id) {
    return new ResponseEntity<>(this.orderService.CancelOrder(id),HttpStatus.OK);
  }

  @RequestMapping(name = "Get All", value = "/get/all",method = RequestMethod.GET)
  public ResponseEntity<List<OrderResponse>> GetAll() {
    return new ResponseEntity<>(this.orderService.GetAll(),HttpStatus.OK);
  }

  @RequestMapping(name = "Get By Id", value = "/get/{id}",method = RequestMethod.GET)
  public ResponseEntity<OrderResponse> GetById(@PathVariable int id) {
    return new ResponseEntity<>(this.orderService.GetById(id),HttpStatus.OK);
  }
}
