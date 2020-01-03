package com.crystalpizaa.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.crystalpizaa.api.dao.interfaces.PizzaRepository;
import com.crystalpizaa.api.mockdata.RepositoryMockData;
import com.crystalpizaa.api.service.PizzaServiceImpl;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.core.PizzaType;
import com.crystalpizaa.api.service.models.core.Size;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import java.util.List;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@DisplayName("Pizza service test cases")
public class PizzaServiceImplTest {


  @Mock
  PizzaRepository pizzaRepository;

  @InjectMocks
  PizzaServiceImpl pizzaService;


  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(pizzaRepository.findAll()).thenReturn(RepositoryMockData.getAllPizza());
    when(pizzaRepository.findById(anyInt())).thenReturn(RepositoryMockData.getPizzaById(1));
    when(pizzaRepository.save(ArgumentMatchers.any())).thenReturn(RepositoryMockData.savePizza());

    doNothing().when(pizzaRepository).deleteById(anyInt());
  }


  @Test
  @Description("Get all pizza test")
  public void getAllPizzasTest() {
    List<Pizza> pizzas = pizzaService.getAll();

    assertNotNull(pizzas);
    assertEquals(2, pizzas.size());
  }

  @Test
  @Description("Get pizza by id test")
  public void getPizzaByIdTest() {
    Pizza pizza = this.pizzaService.get(1);
    assertNotNull(pizza);
  }


  @Test
  @Description("Save pizza test")
  public void savePizzaTest() {
    Pizza pizza = new Pizza();
    pizza.setId(0);
    pizza.setName("Pepprr-cord cheeeze pizza");
    pizza.setDescription("Pepprr-cord cheeeze pizza");
    pizza.setType(PizzaType.Veg);
    pizza.setSize(Size.Large);
    pizza.setPrice(100);

    Pizza savedPizza = this.pizzaService.save(pizza);
    assertNotNull(savedPizza);
    assertNotEquals(0, savedPizza.getId());
  }

  @Test
  @Description("Remove pizza test")
  public void removePizzaTest() {
    assertEquals(true, this.pizzaService.remove(1));
  }

  @Test
  @Description("Validation failure | Exception test")
  public void savePizzaValidationFailTest() {
    Exception exception = new Exception();
    try
    {
      this.pizzaService.save(new Pizza());
    } catch (ValidationException ex) {
      exception = ex;
    }

    assertTrue(exception.getClass() == ValidationException.class);
  }


}
