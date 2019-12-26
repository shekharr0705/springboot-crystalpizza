package com.crystalpizaa.api.service;

import com.crystalpizaa.api.service.models.validation.ValidationException;
import java.util.List;
import com.crystalpizaa.api.service.models.core.Pizza;

public interface PizzaService {

  Pizza save(Pizza pizza);

  List<Pizza> getAll();

  Pizza get(int id);

  boolean remove(int id);
}
