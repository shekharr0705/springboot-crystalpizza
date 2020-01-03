package com.crystalpizaa.api.service;

import com.crystalpizaa.api.AppLogger;
import com.crystalpizaa.api.dao.interfaces.PizzaRepository;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.validation.DataNotFoundException;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import com.crystalpizaa.api.service.translators.PizzaTranslator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaServiceImpl implements PizzaService {

  @Autowired
  private PizzaRepository pizzaRepository;

  @Override
  public Pizza save(Pizza pizza) {

    if (EnsureValid(pizza)) {
      try {
        com.crystalpizaa.api.dao.entities.Pizza pizzaObj = PizzaTranslator.ToDaoModel(pizza);

        com.crystalpizaa.api.dao.entities.Pizza result = this.pizzaRepository.save(pizzaObj);

        return PizzaTranslator.ToServiceModel(result);
      } catch (Exception ex) {
        if (ex instanceof ValidationException) {
          throw ex;
        }
        AppLogger
            .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
      }

    }
    return null;
  }

  @Override
  public List<Pizza> getAll() {

    try {
      List<com.crystalpizaa.api.dao.entities.Pizza> pizzaObjs = this.pizzaRepository.findAll();
      if (pizzaObjs == null || pizzaObjs.size() == 0) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails("Pizzas not found");
        AppLogger.log(this.getClass().getName(), "Pizzas not found", Level.INFO);
        throw new DataNotFoundException(error);
      }
      return PizzaTranslator.ToServiceModel(pizzaObjs);
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
  public Pizza get(int id) {

    try {

      com.crystalpizaa.api.dao.entities.Pizza pizzaObj = this.pizzaRepository.findById(id)
          .orElse(null);
      if (pizzaObj == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested Pizza of id %d not found", id));
        AppLogger
            .log(this.getClass().getName(), String.format("Requested Pizza of id %d not found", id),
                Level.INFO);
        throw new DataNotFoundException(error);
      }

      return PizzaTranslator.ToServiceModel(pizzaObj);
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
  public boolean remove(int id) {
    try {
      com.crystalpizaa.api.dao.entities.Pizza pizzaObj = this.pizzaRepository.findById(id)
          .orElse(null);
      if (pizzaObj == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested pizza of id %d not found", id));
        AppLogger
            .log(this.getClass().getName(), String.format("Requested pizza of id %d not found", id),
                Level.INFO);
        throw new DataNotFoundException(error);
      }
      this.pizzaRepository.deleteById(id);
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
