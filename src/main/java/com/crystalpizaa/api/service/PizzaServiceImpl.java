package com.crystalpizaa.api.service;

import com.crystalpizaa.api.dao.interfaces.PizzaRepository;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import com.crystalpizaa.api.service.translators.PizzaTranslator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService {

  @Autowired
  private PizzaRepository pizzaRepository;

  @Override
  public Pizza save(Pizza pizza)  {

    if (EnsureValid(pizza)) {
      com.crystalpizaa.api.dao.entities.Pizza pizzaObj = PizzaTranslator.ToDaoModel(pizza);

      com.crystalpizaa.api.dao.entities.Pizza result = this.pizzaRepository.save(pizzaObj);

      return PizzaTranslator.ToServiceModel(result);
    }
    return null;
  }

  @Override
  public List<Pizza> getAll() {

    List<com.crystalpizaa.api.dao.entities.Pizza> pizzaObjs = this.pizzaRepository.findAll();

    return PizzaTranslator.ToServiceModel(pizzaObjs);
  }

  @Override
  public Pizza get(int id) {

    com.crystalpizaa.api.dao.entities.Pizza pizzaObj = this.pizzaRepository.getOne(id);

    return PizzaTranslator.ToServiceModel(pizzaObj);
  }

  @Override
  public boolean remove(int id) {
    this.pizzaRepository.deleteById(id);

    return true;
  }

  private boolean EnsureValid(Pizza pizza) {

    List<ValidationInfo> failedValidations = new ArrayList<>();

    if (pizza.getName() == null || pizza.getName().isBlank()) {
      failedValidations.add(new ValidationInfo("name", "Value Required"));
    }

    if (pizza.getSize() == null) {
      failedValidations.add(new ValidationInfo("size", "Value Required"));
    }

    if (pizza.getType() == null) {
      failedValidations.add(new ValidationInfo("type", "Value Required"));
    }

    if (pizza.getPrice() < 1) {
      failedValidations.add(new ValidationInfo("price", "Invalid Value"));
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
