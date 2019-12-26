package com.crystalpizaa.api.service.translators;

import java.util.ArrayList;
import java.util.List;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.core.PizzaType;

public class PizzaTranslator {

  public static Pizza ToServiceModel(com.crystalpizaa.api.dao.entities.Pizza daoObj) {
    Pizza pizza = new Pizza();
    pizza.setId(daoObj.getId());
    pizza.setName(daoObj.getName());
    pizza.setDescription(daoObj.getDescription());
    pizza.setPrice(daoObj.getPrice());
    pizza.setType(ToServiceModel(daoObj.getType()));
    pizza.setSize(CommonTranslator.ToServiceModel(daoObj.getSize()));
    return pizza;
  }


  public static com.crystalpizaa.api.dao.entities.Pizza ToDaoModel(Pizza serviceObj) {
    com.crystalpizaa.api.dao.entities.Pizza pizza = new com.crystalpizaa.api.dao.entities.Pizza();
    pizza.setId(serviceObj.getId());
    pizza.setName(serviceObj.getName());
    pizza.setDescription(serviceObj.getDescription());
    pizza.setPrice(serviceObj.getPrice());
    pizza.setType(ToDaoModel(serviceObj.getType()));
    pizza.setSize(CommonTranslator.ToDaoModel((serviceObj.getSize())));
    return pizza;
  }

  public static List<Pizza> ToServiceModel(List<com.crystalpizaa.api.dao.entities.Pizza> daoObjs) {

    List<Pizza> results = new ArrayList<Pizza>();

    for (com.crystalpizaa.api.dao.entities.Pizza daoObj : daoObjs) {
      results.add(ToServiceModel(daoObj));
    }
    return results;
  }


  private static PizzaType ToServiceModel(com.crystalpizaa.api.dao.entities.PizzaType daoObj) {
    switch (daoObj) {
      case Veg:
        return PizzaType.Veg;
      case NonVeg:
        return PizzaType.NonVeg;
    }
    return PizzaType.Veg;
  }

  private static com.crystalpizaa.api.dao.entities.PizzaType ToDaoModel(PizzaType serviceObj) {
    switch (serviceObj) {
      case Veg:
        return com.crystalpizaa.api.dao.entities.PizzaType.Veg;
      case NonVeg:
        return com.crystalpizaa.api.dao.entities.PizzaType.NonVeg;
    }
    return com.crystalpizaa.api.dao.entities.PizzaType.Veg;
  }
}
