package com.crystalpizaa.api.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crystalpizaa.api.mockdata.ServiceMockData;
import com.crystalpizaa.api.service.OrderServiceImpl;
import com.crystalpizaa.api.service.models.core.OrderItem;
import com.crystalpizaa.api.service.models.requestresponse.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("User controller test cases")
@WebMvcTest
public class OrderControllerTest {

  @Mock
  private OrderServiceImpl orderService;

  @InjectMocks
  private OrderController orderController;

  private MockMvc mockMvc;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(orderService.getAll()).thenReturn(ServiceMockData.getAllOrders());
    when(orderService.getById(anyInt())).thenReturn(ServiceMockData.getOrderById(1));
    when(orderService.placeOrder(ArgumentMatchers.any())).thenReturn(ServiceMockData.placeOrder());
    when(orderService.getPrice(ArgumentMatchers.any())).thenReturn(ServiceMockData.getPriceResponse());
    when(orderService.cancelOrder(anyInt())).thenReturn(true);
    mockMvc = MockMvcBuilders
        .standaloneSetup(orderController)
        .build();
  }

  @Test
  @Description("Get all orders controller method test")
  public void getAllTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/orders/get/all")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Get order by id controller method test")
  public void getByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/orders/get/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }


  @Test
  @Description("Order place controller method test")
  public void placeOrderTest() throws Exception {
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setUserId(1);
    orderRequest.setAddressSameAsUserAddress(false);
    orderRequest.setAddress("Geneva Switserland");
    orderRequest.setPizzas(new ArrayList<>());
    orderRequest.setAddOns(new ArrayList<>());

    OrderItem pizzaOrderItem = new OrderItem();
    pizzaOrderItem.setId(1);
    pizzaOrderItem.setQuantity(2);

    OrderItem addOnOrderItem = new OrderItem();
    addOnOrderItem.setId(1);
    addOnOrderItem.setQuantity(2);

    orderRequest.getAddOns().add(addOnOrderItem);
    orderRequest.getPizzas().add(pizzaOrderItem);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/orders/place")
        .content(new ObjectMapper().writeValueAsString(orderRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Get order price controller method test")
  public void getOrderPriceTest() throws Exception {
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setUserId(1);
    orderRequest.setAddressSameAsUserAddress(false);
    orderRequest.setAddress("Geneva Switserland");
    orderRequest.setPizzas(new ArrayList<>());
    orderRequest.setAddOns(new ArrayList<>());

    OrderItem pizzaOrderItem = new OrderItem();
    pizzaOrderItem.setId(1);
    pizzaOrderItem.setQuantity(2);

    OrderItem addOnOrderItem = new OrderItem();
    addOnOrderItem.setId(1);
    addOnOrderItem.setQuantity(2);

    orderRequest.getAddOns().add(addOnOrderItem);
    orderRequest.getPizzas().add(pizzaOrderItem);

    this.mockMvc.perform(MockMvcRequestBuilders.post("/orders/get-price")
        .content(new ObjectMapper().writeValueAsString(orderRequest))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Cancel order controller method test")
  public void removeTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/orders/cancel/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }
}
