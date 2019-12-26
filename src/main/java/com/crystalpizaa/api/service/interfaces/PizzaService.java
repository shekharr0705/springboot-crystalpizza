package com.crystalpizaa.api.service.interfaces;

import com.crystalpizaa.api.service.models.validation.ValidationException;
import java.util.List;
import com.crystalpizaa.api.service.models.core.Pizza;

public interface PizzaService {

  Pizza Save(Pizza pizza) throws ValidationException;

  List<Pizza> GetAll();

  Pizza Get(int id);

  boolean Remove(int id);
}
