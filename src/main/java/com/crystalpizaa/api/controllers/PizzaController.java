package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.interfaces.PizzaService;
import com.crystalpizaa.api.service.models.core.Pizza;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "pizza", value = "/pizzas")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;


  @RequestMapping(name = "GetAll", value = "/get/all", method = RequestMethod.GET)
  public ResponseEntity<List<Pizza>> GetAll() {
    return new ResponseEntity<>(this.pizzaService.GetAll(), HttpStatus.OK);
  }

  @RequestMapping(name = "GetById", value = "/get/{id}", method = RequestMethod.GET)
  public ResponseEntity<Pizza> GetById(@PathVariable int id) {
    return new ResponseEntity<>(this.pizzaService.Get(id), HttpStatus.OK);
  }

  @RequestMapping(name = "Save", value = "/save", method = RequestMethod.POST)
  public ResponseEntity<Pizza> Save(@RequestBody Pizza pizza) {

    return new ResponseEntity<>(this.pizzaService.Save(pizza), HttpStatus.CREATED);

  }

  @RequestMapping(name = "Delete", value = "/remove/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> Remove(@PathVariable int id) {
    return new ResponseEntity<>(this.pizzaService.Remove(id), HttpStatus.OK);
  }
}
