package com.crystalpizaa.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.crystalpizaa.api.dao.interfaces.AddOnRepository;
import com.crystalpizaa.api.dao.interfaces.OrderRepository;
import com.crystalpizaa.api.dao.interfaces.PizzaRepository;
import com.crystalpizaa.api.dao.interfaces.UserRepository;
import com.crystalpizaa.api.mockdata.RepositoryMockData;
import com.crystalpizaa.api.service.OrderServiceImpl;
import com.crystalpizaa.api.service.models.core.OrderItem;
import com.crystalpizaa.api.service.models.requestresponse.OrderRequest;
import com.crystalpizaa.api.service.models.requestresponse.OrderResponse;
import com.crystalpizaa.api.service.models.requestresponse.PriceRequest;
import com.crystalpizaa.api.service.models.requestresponse.PriceResponse;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Order service test cases")
public class OrderServiceImplTest {
  @Mock
  OrderRepository orderRepository;

  @Mock
  PizzaRepository pizzaRepository;

  @Mock
  AddOnRepository addOnRepository;

  @Mock
  UserRepository userRepository;

  @InjectMocks
  OrderServiceImpl orderService;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(orderRepository.findAll()).thenReturn(RepositoryMockData.getAllOrders());
    when(orderRepository.findById(anyInt())).thenReturn(RepositoryMockData.getOrderById(1));
    when(orderRepository.save(ArgumentMatchers.any())).thenReturn(RepositoryMockData.saveOrder());
    doNothing().when(orderRepository).deleteById(anyInt());

    when(pizzaRepository.findById(anyInt())).thenReturn(RepositoryMockData.getPizzaById(1));
    when(addOnRepository.findById(anyInt())).thenReturn(RepositoryMockData.getAddOnById(1));

    when(userRepository.getOne(anyInt())).thenReturn(RepositoryMockData.getUserById(1).orElse(null));
  }

  @Test
  @Description("Get all orders test")
  public void getAllOrdersTest() {
    List<OrderResponse> orders = orderService.getAll();

    assertNotNull(orders);
    assertEquals(2, orders.size());
  }

  @Test
  @Description("Get order by id test")
  public void getOrderIdTest() {
    OrderResponse orderResponse = this.orderService.getById(1);
    assertNotNull(orderResponse);
  }


  @Test
  @Description("Save order test")
  public void saveOrderTest() {

    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setUserId(1);
    orderRequest.setAddress("Moshi pune");
    orderRequest.setAddressSameAsUserAddress(false);

    OrderItem addOnitem= new OrderItem();
    addOnitem.setId(1);
    addOnitem.setQuantity(2);

    OrderItem pizzaItem = new OrderItem();
    pizzaItem.setId(1);
    pizzaItem.setQuantity(2);

    orderRequest.setAddOns(new ArrayList<>());
    orderRequest.setPizzas(new ArrayList<>());

    orderRequest.getAddOns().add(addOnitem);
    orderRequest.getPizzas().add(pizzaItem);

    OrderResponse orderResponse = this.orderService.placeOrder(orderRequest);
    assertNotNull(orderResponse);
    assertNotEquals(0, orderResponse.getOrderId());
  }

  @Test
  @Description("Get order price test")
  public void getOrderPriceTest() {
    PriceRequest priceRequest = new PriceRequest();
    OrderItem addOnitem= new OrderItem();
    addOnitem.setId(1);
    addOnitem.setQuantity(2);

    OrderItem pizzaItem = new OrderItem();
    pizzaItem.setId(1);
    pizzaItem.setQuantity(2);
    priceRequest.setAddOns(new ArrayList<>());
    priceRequest.getAddOns().add(addOnitem);

    priceRequest.setPizzas(new ArrayList<>());
    priceRequest.getPizzas().add(pizzaItem);

    PriceResponse priceResponse= this.orderService.getPrice(priceRequest);
    assertNotNull(priceResponse);

  }

  @Test
  @Description("Cancel test")
  public void cancelOrderTest() {
    assertEquals(true, this.orderService.cancelOrder(1));
  }

  @Test
  @Description("Validation failure | Exception  for get price test")
  public void getOrderPriceValidationFailTest() {
    Exception exception = new Exception();
    try
    {
      this.orderService.getPrice(new PriceRequest());
    } catch (ValidationException ex) {
      exception = ex;
    }

    assertTrue(exception.getClass() == ValidationException.class);
  }

  @Test
  @Description("Validation failure | Exception  for place order test")
  public void placeOrderValidationFailTest() {
    Exception exception = new Exception();
    try
    {
      OrderRequest request = new OrderRequest();
      request.setAddressSameAsUserAddress(false);
      this.orderService.placeOrder(request);
    } catch (ValidationException ex) {
      exception = ex;
    }

    assertTrue(exception.getClass() == ValidationException.class);
  }
}
