package com.crystalpizaa.api.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crystalpizaa.api.mockdata.ServiceMockData;
import com.crystalpizaa.api.service.PizzaServiceImpl;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.core.PizzaType;
import com.crystalpizaa.api.service.models.core.Size;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pizza controller test cases")
@WebMvcTest
public class PizzaControllerTest {

  @Mock
  private PizzaServiceImpl pizzaService;

  @InjectMocks
  private PizzaController pizzaController;

  private MockMvc mockMvc;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(pizzaService.getAll()).thenReturn(ServiceMockData.getAllPizza());
    when(pizzaService.get(anyInt())).thenReturn(ServiceMockData.getPizzaById(1));
    when(pizzaService.save(ArgumentMatchers.any())).thenReturn(ServiceMockData.savePizza());
    when(pizzaService.remove(anyInt())).thenReturn(true);
    mockMvc = MockMvcBuilders
        .standaloneSetup(pizzaController)
        .build();
  }


  @Test
  @Description("Get all pizza controller method test")
  public void getAllTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/pizzas/get/all")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Get pizza by id controller method test")
  public void getByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/pizzas/get/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Save Pizza controller method test")
  public void saveTest() throws Exception {
    Pizza pizza = new Pizza();
    pizza.setName("New Peppere pizza");
    pizza.setDescription("New Peppere pizza");
    pizza.setPrice(550);
    pizza.setSize(Size.Medium);
    pizza.setType(PizzaType.Veg);
    String value = new ObjectMapper().writeValueAsString(pizza);
    this.mockMvc.perform(MockMvcRequestBuilders.post("/pizzas/save")
        .content(new ObjectMapper().writeValueAsString(pizza))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Remove pizza controller method test")
  public void removeTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/pizzas/remove/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }
}
