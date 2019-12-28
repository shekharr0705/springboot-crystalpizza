package com.crystalpizaa.api.mockdata;

import com.crystalpizaa.api.dao.entities.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositoryMockData {


  private static List<Pizza> pizzas = new ArrayList<>();
  private static List<AddOn> addOns = new ArrayList<>();
  private static List<User> users = new ArrayList<>();
  private static List<Order> orders = new ArrayList<>();

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

    Order order1 = new Order();
    order1.setId(1);
    order1.setOrderDate(LocalDateTime.now());
    order1.setAddress("Wakad Pune");
    order1.setUser(getUserById(1));

    OrderItem orderItem1 = new OrderItem();
    orderItem1.setQuantity(2);
    orderItem1.setType(ComponentType.Pizza);
    orderItem1.setComponentId(1);
    orderItem1.setId(1);

    OrderItem orderItem2 = new OrderItem();
    orderItem2.setQuantity(2);
    orderItem2.setType(ComponentType.AddOn);
    orderItem2.setComponentId(2);
    orderItem2.setId(2);

    order1.setOrderItems(new ArrayList<>());
    order1.getOrderItems().add(orderItem1);
    order1.getOrderItems().add(orderItem2);

    Order order2 = new Order();
    order2.setId(2);
    order2.setOrderDate(LocalDateTime.now());
    order2.setAddress("Viman nagar Pune");
    order2.setUser(getUserById(1));

    OrderItem orderItem3 = new OrderItem();
    orderItem3.setQuantity(3);
    orderItem3.setType(ComponentType.Pizza);
    orderItem3.setComponentId(1);
    orderItem3.setId(4);

    OrderItem orderItem4 = new OrderItem();
    orderItem4.setQuantity(1);
    orderItem4.setType(ComponentType.AddOn);
    orderItem4.setComponentId(2);
    orderItem4.setId(5);

    order2.setOrderItems(new ArrayList<>());
    order2.getOrderItems().add(orderItem4);
    order2.getOrderItems().add(orderItem3);

    orders.add(order1);
    orders.add(order2);

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

  public static List<Order> getAllOrders() {
    return orders;
  }
  public static Order getOrderById(Integer id) {
    return orders.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
  }

  public static Order saveOrder() {
    Order order = new Order();
    order.setId(3);
    order.setOrderDate(LocalDateTime.now());
    order.setAddress("Viman nagar Pune");
    order.setUser(getUserById(1));

    OrderItem orderItem = new OrderItem();
    orderItem.setQuantity(3);
    orderItem.setType(ComponentType.Pizza);
    orderItem.setComponentId(1);
    orderItem.setId(4);

    order.setOrderItems(new ArrayList<>());
    order.getOrderItems().add(orderItem);
    return order;
  }

}
