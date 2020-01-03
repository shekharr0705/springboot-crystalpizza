package com.crystalpizaa.api.service;

import com.crystalpizaa.api.AppLogger;
import com.crystalpizaa.api.dao.entities.ComponentType;
import com.crystalpizaa.api.dao.entities.Order;
import com.crystalpizaa.api.dao.entities.User;
import com.crystalpizaa.api.dao.interfaces.AddOnRepository;
import com.crystalpizaa.api.dao.interfaces.OrderRepository;
import com.crystalpizaa.api.dao.interfaces.PizzaRepository;
import com.crystalpizaa.api.dao.interfaces.UserRepository;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.models.core.Component;
import com.crystalpizaa.api.service.models.core.OrderItem;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.core.PriceModel;
import com.crystalpizaa.api.service.models.requestresponse.OrderRequest;
import com.crystalpizaa.api.service.models.requestresponse.OrderResponse;
import com.crystalpizaa.api.service.models.requestresponse.PriceRequest;
import com.crystalpizaa.api.service.models.requestresponse.PriceResponse;
import com.crystalpizaa.api.service.models.validation.DataNotFoundException;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import com.crystalpizaa.api.service.translators.AddOnTranslator;
import com.crystalpizaa.api.service.translators.OrderTranslator;
import com.crystalpizaa.api.service.translators.PizzaTranslator;
import com.crystalpizaa.api.service.translators.UserTranslator;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  PizzaRepository pizzaRepository;

  @Autowired
  AddOnRepository addOnRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  OrderRepository orderRepository;

  @Override
  public PriceResponse getPrice(PriceRequest request) {
    try {
      if (this.ensureValid(request)) {

        PriceResponse response = new PriceResponse();

        response.setPizzas(GetPizzasPriceDetailsList(request.getPizzas()));

        response.setAddOns(getAddOnsPriceDetailsList(request.getAddOns()));

        return response;
      }
    } catch (Exception ex) {
      if (ex instanceof ValidationException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }

    return null;
  }

  @Override
  public OrderResponse placeOrder(OrderRequest request) {

    try {
      if (this.ensureValid(request)) {
        OrderResponse response = new OrderResponse();

        response.setPizzas(GetPizzasPriceDetailsList(request.getPizzas()));
        response.setAddOns(getAddOnsPriceDetailsList(request.getAddOns()));

        Order order = this.orderRepository.save(GenerateOrder(request, response.getTotal()));

        response.setOrderId(order.getId());
        response.setCustomerDetails(UserTranslator.toServiceModel(order.getUser()));
        response.setDeliveryAddress(order.getAddress());
        response.setOrderDate(order.getOrderDate());

        return response;
      }
    } catch (Exception ex) {
      if (ex instanceof ValidationException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }

    return null;
  }

  @Override
  public List<OrderResponse> getAll() {

    try {

      List<OrderResponse> orderResponses = new ArrayList<>();

      List<Order> orders = this.orderRepository.findAll();

      if (orders == null || orders.size() == 0) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails("Orders not found");
        AppLogger.log(this.getClass().getName(), "Orders not found", Level.INFO);
        throw new DataNotFoundException(error);
      }

      if (orders.size() > 0) {
        for (Order order : orders) {

          OrderResponse response = new OrderResponse();
          response.setOrderId(order.getId());
          response.setDeliveryAddress(order.getAddress());
          response.setCustomerDetails(UserTranslator.toServiceModel(order.getUser()));

          List<com.crystalpizaa.api.dao.entities.OrderItem> dao_pizzas = order.getOrderItems()
              .stream()
              .filter(x -> x.getType() == ComponentType.Pizza)
              .collect(Collectors.toList());

          List<com.crystalpizaa.api.dao.entities.OrderItem> dao_addOns = order.getOrderItems()
              .stream()
              .filter(x -> x.getType() == ComponentType.AddOn).collect(
                  Collectors.toList());

          List<OrderItem> pizzas = GetServiceOrderItems(dao_pizzas);
          List<OrderItem> addOn = GetServiceOrderItems(dao_addOns);

          response.setPizzas(GetPizzasPriceDetailsList(pizzas));
          response.setAddOns(getAddOnsPriceDetailsList(addOn));

          response.setOrderDate(order.getOrderDate());

          orderResponses.add(response);

        }
      }

      return orderResponses;
    } catch (Exception ex) {
      if (ex instanceof DataNotFoundException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }

    return null;
  }

  @Override
  public OrderResponse getById(int id) {
    try {
      OrderResponse response = new OrderResponse();

      Order order = this.orderRepository.findById(id).orElse(null);

      if (order == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested order of id %d not found", id));

        throw new DataNotFoundException(error);
      }

      response.setOrderId(order.getId());
      response.setDeliveryAddress(order.getAddress());
      response.setCustomerDetails(UserTranslator.toServiceModel(order.getUser()));

      List<com.crystalpizaa.api.dao.entities.OrderItem> dao_pizzas = order.getOrderItems().stream()
          .filter(x -> x.getType() == ComponentType.Pizza)
          .collect(Collectors.toList());

      List<com.crystalpizaa.api.dao.entities.OrderItem> dao_addOns = order.getOrderItems().stream()
          .filter(x -> x.getType() == ComponentType.AddOn).collect(
              Collectors.toList());

      List<OrderItem> pizzas = GetServiceOrderItems(dao_pizzas);
      List<OrderItem> addOn = GetServiceOrderItems(dao_addOns);

      response.setPizzas(GetPizzasPriceDetailsList(pizzas));
      response.setAddOns(getAddOnsPriceDetailsList(addOn));

      response.setOrderDate(order.getOrderDate());

      return response;
    } catch (Exception ex) {
      if (ex instanceof DataNotFoundException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }
    return null;
  }

  @Override
  public Boolean cancelOrder(int orderId) {

    try {
      Order order = this.orderRepository.findById(orderId).orElse(null);

      if (order == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested order of id %d not found", orderId));

        throw new DataNotFoundException(error);
      }
      this.orderRepository.deleteById(orderId);
      return true;
    } catch (Exception ex) {
      if (ex instanceof DataNotFoundException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }
    return false;
  }


  private Order GenerateOrder(OrderRequest request, double total) {
    Order order = new Order();
    User user = userRepository.getOne(request.getUserId());

    List<com.crystalpizaa.api.dao.entities.OrderItem> daoOrderItems = GetDaoOrderItems(request);

    if (request.getAddressSameAsUserAddress()) {
      order.setAddress(user.getAddress());
    } else {
      order.setAddress(request.getAddress());
    }

    order.setUser(user);
    order.setOrderItems(daoOrderItems);
    order.setOrderDate(LocalDateTime.now());
    order.setTotal(total);

    return order;
  }

  private List<com.crystalpizaa.api.dao.entities.OrderItem> GetDaoOrderItems(OrderRequest request) {
    List<com.crystalpizaa.api.dao.entities.OrderItem> daoOrderItems = new ArrayList<>();
    daoOrderItems.addAll(OrderTranslator.toDaoEntity(request.getPizzas(), ComponentType.Pizza));
    daoOrderItems.addAll(OrderTranslator.toDaoEntity(request.getAddOns(), ComponentType.AddOn));

    return daoOrderItems;
  }

  private List<OrderItem> GetServiceOrderItems(
      List<com.crystalpizaa.api.dao.entities.OrderItem> daoOrderItems) {
    List<OrderItem> orderItems = new ArrayList<>();

    for (com.crystalpizaa.api.dao.entities.OrderItem daoItem : daoOrderItems) {
      OrderItem item = new OrderItem();
      item.setId(daoItem.getComponentId());
      item.setQuantity(daoItem.getQuantity());
      orderItems.add(item);
    }

    return orderItems;
  }

  private List<PriceModel> GetPizzasPriceDetailsList(List<OrderItem> orderItems) {
    List<PriceModel> priceDetailsList = new ArrayList<PriceModel>();

    if (orderItems.size() > 0) {
      for (OrderItem orderItem : orderItems) {
        Pizza pizza = PizzaTranslator
            .ToServiceModel(this.pizzaRepository.findById(orderItem.getId()).orElse(null));
        if (pizza != null) {
          priceDetailsList.add(this.getPriceDetails(pizza, orderItem));
        }
      }
    }
    return priceDetailsList;
  }

  private List<PriceModel> getAddOnsPriceDetailsList(List<OrderItem> orderItems) {
    List<PriceModel> priceDetailsList = new ArrayList<PriceModel>();

    if (orderItems.size() > 0) {
      for (OrderItem orderItem : orderItems) {
        AddOn addOn = AddOnTranslator
            .toServiceModel(this.addOnRepository.findById(orderItem.getId()).orElse(null));
        if (addOn != null) {
          priceDetailsList.add(this.getPriceDetails(addOn, orderItem));
        }
      }
    }
    return priceDetailsList;
  }

  private PriceModel getPriceDetails(Component c, OrderItem item) {
    PriceModel priceItem = new PriceModel();

    priceItem.setId(item.getId());
    priceItem.setQuantity(item.getQuantity());
    priceItem.setUnitCost(c.getPrice());

    return priceItem;
  }


  private boolean ensureValid(OrderRequest orderRequest) {

    List<ValidationInfo> failedValidations = new ArrayList<>();

    if ((orderRequest.getAddOns() == null || orderRequest.getAddOns().size() == 0) && (
        orderRequest.getPizzas() == null || orderRequest.getPizzas().size() == 0)) {
      failedValidations.add(new ValidationInfo("AddOn/Pizzas",
          "Atleast one Pizza or one AddOn should be there in order"));
    }

    if (orderRequest.getUserId() < 1) {
      failedValidations.add(new ValidationInfo("UserId", "Invalid UserId"));
    }

    if (orderRequest.getAddressSameAsUserAddress() == false && (orderRequest.getAddress() == null
        || orderRequest.getAddress().isBlank())) {
      failedValidations.add(new ValidationInfo("address", "Value Required"));
    }

    if (failedValidations.size() > 0) {
      ErrorInfo errorInfo = new ErrorInfo();
      errorInfo.setTitle("Error occurred while processing request");
      errorInfo.setFailedValidationFields(failedValidations);
      throw new ValidationException(errorInfo);
    }

    return true;
  }

  private boolean ensureValid(PriceRequest priceRequest) {

    List<ValidationInfo> failedValidations = new ArrayList<>();

    if ((priceRequest.getAddOns() == null || priceRequest.getAddOns().size() == 0) && (
        priceRequest.getPizzas() == null || priceRequest.getPizzas().size() == 0)) {
      failedValidations.add(new ValidationInfo("AddOn/Pizzas",
          "Atleast one Pizza or one AddOn should be there in order"));
    }

    if (failedValidations.size() > 0) {
      ErrorInfo errorInfo = new ErrorInfo();
      errorInfo.setTitle("Error occurred while processing request");
      errorInfo.setFailedValidationFields(failedValidations);
      throw new ValidationException(errorInfo);
    }

    return true;
  }
}
