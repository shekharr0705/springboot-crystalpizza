package com.crystalpizaa.api.mockdata;

import com.crystalpizaa.api.dao.entities.ComponentType;
import com.crystalpizaa.api.service.models.core.*;
import com.crystalpizaa.api.service.models.requestresponse.OrderResponse;
import com.crystalpizaa.api.service.models.requestresponse.PriceResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceMockData {
  private static List<Pizza> pizzas = new ArrayList<>();
  private static List<AddOn> addOns = new ArrayList<>();
  private static List<User> users = new ArrayList<>();
  private static List<OrderResponse> orders = new ArrayList<>();

  static {
    Pizza pizza1 = new Pizza();
    pizza1.setId(1);
    pizza1.setName("crisp capsicum with spicy red paprika Peppy Paneer");
    pizza1.setSize(Size.Large);
    pizza1.setType(PizzaType.Veg);
    pizza1.setDescription("crisp capsicum with spicy red paprika Peppy Paneer");

    Pizza pizza2 = new Pizza();
    pizza2.setId(2);
    pizza2.setName("corn & paneer Deluxe Veggie");
    pizza2.setSize(Size.Large);
    pizza2.setType(PizzaType.Veg);
    pizza2.setDescription("corn & paneer Deluxe Veggie");

    pizzas.add(pizza1);
    pizzas.add(pizza2);

    AddOn addOn1 = new AddOn();
    addOn1.setId(1);
    addOn1.setName("Pepsi 750ml1");
    addOn1.setSize(Size.Large);
    addOn1.setType(AddOnType.Beverage);
    addOn1.setDescription("Corbonated water with sugar");

    AddOn addOn2 = new AddOn();

    addOn2.setId(2);
    addOn2.setName("Coke 750ml");
    addOn2.setSize(Size.Large);
    addOn2.setType(AddOnType.Beverage);
    addOn2.setDescription("Corbonated water with sugar");

    addOns.add(addOn1);
    addOns.add(addOn2);

    User user1 = new User();
    user1.setId(1);
    user1.setName("Amit");
    user1.setEmailAddress("amit@gmail.com");
    user1.setAddress("Pune");
    user1.setContact("8884004488");

    User user2 = new User();
    user2.setId(2);
    user2.setName("Raj");
    user2.setEmailAddress("raj@gmail.com");
    user2.setAddress("Pune");
    user2.setContact("8888085451");

    users.add(user1);
    users.add(user2);


    OrderResponse orderResponse1= new OrderResponse();
    orderResponse1.setOrderId(1);
    orderResponse1.setOrderDate(LocalDateTime.now());
    orderResponse1.setTotal(1500);
    orderResponse1.setCustomerDetails(user1);
    orderResponse1.setDeliveryAddress("Rahatani pune");

    PriceModel orderItem1 = new PriceModel();
    orderItem1.setQuantity(2);
    orderItem1.setUnitCost(100);
    orderItem1.setId(1);

    PriceModel orderItem2 = new PriceModel();
    orderItem2.setQuantity(2);
    orderItem2.setUnitCost(200);
    orderItem2.setId(1);

    orderResponse1.setPizzas(new ArrayList<>());
    orderResponse1.getPizzas().add(orderItem1);

    orderResponse1.setAddOns(new ArrayList<>());
    orderResponse1.getAddOns().add(orderItem2);

    OrderResponse orderResponse2= new OrderResponse();
    orderResponse2.setOrderId(2);
    orderResponse2.setOrderDate(LocalDateTime.now());
    orderResponse2.setTotal(1500);
    orderResponse2.setCustomerDetails(user1);
    orderResponse2.setDeliveryAddress("Moshi pune");

    PriceModel orderItem3 = new PriceModel();
    orderItem3.setQuantity(2);
    orderItem3.setUnitCost(150);
    orderItem3.setId(1);

    PriceModel orderItem4 = new PriceModel();
    orderItem4.setQuantity(1);
    orderItem4.setUnitCost(220);
    orderItem4.setId(1);

    orderResponse2.setPizzas(new ArrayList<>());
    orderResponse2.getPizzas().add(orderItem3);

    orderResponse2.setAddOns(new ArrayList<>());
    orderResponse2.getAddOns().add(orderItem4);

    orders.add(orderResponse1);
    orders.add(orderResponse2);
  }

  public static List<Pizza> getAllPizza() {
    return pizzas;
  }

  public static Pizza getPizzaById(Integer id) {
    return pizzas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
  }

  public static Pizza savePizza() {
    Pizza pizza = new Pizza();
    pizza.setId(3);
    pizza.setName("corn & paneer Deluxe Veggie new");
    pizza.setSize(Size.Large);
    pizza.setType(PizzaType.Veg);
    pizza.setDescription("corn & paneer Deluxe Veggie new");
    //pizzas.add(pizza);

    return pizza;
  }


  public static List<AddOn> getAllAddOns() {
    return addOns;
  }

  public static AddOn getAddOnById(Integer id) {
    return addOns.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
  }

  public static AddOn saveAddon() {
    AddOn addOn = new AddOn();
    addOn.setId(3);
    addOn.setName("Pepse (750ml)");
    addOn.setSize(Size.Large);
    addOn.setType(AddOnType.Beverage);
    addOn.setDescription("Corbonated water with sugar");
    //addOns.add(addOn);

    return addOn;
  }


  public static List<User> getAllUsers() {
    return users;
  }

  public static User getUserById(Integer id) {
    return users.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
  }

  public static User saveUser() {
    User user = new User();
    user.setId(3);
    user.setName("Raj");
    user.setEmailAddress("raj@gmail.com");
    user.setAddress("Pune");
    user.setContact("8888085451");

    return user;
  }

  public static List<OrderResponse> getAllOrders() {
    return orders;
  }

  public static OrderResponse getOrderById(Integer id) {
    return orders.stream().filter(p -> p.getOrderId() == id).findFirst().orElse(null);
  }

  public static OrderResponse placeOrder() {
    return orders.stream().filter(p -> p.getOrderId() == 1).findFirst().orElse(null);
  }

  public static PriceResponse getPriceResponse() {
    PriceResponse priceResponse = new PriceResponse();
    priceResponse.setAddOns(new ArrayList<>());
    priceResponse.setPizzas(new ArrayList<>());

    PriceModel orderItem1 = new PriceModel();
    orderItem1.setQuantity(2);
    orderItem1.setUnitCost(100);
    orderItem1.setId(1);

    PriceModel orderItem2 = new PriceModel();
    orderItem2.setQuantity(2);
    orderItem2.setUnitCost(200);
    orderItem2.setId(2);

    priceResponse.getPizzas().add(orderItem1);
    priceResponse.getAddOns().add(orderItem2);
    return priceResponse;
  }
}
