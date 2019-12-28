package com.crystalpizaa.api.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crystalpizaa.api.mockdata.ServiceMockData;
import com.crystalpizaa.api.service.AddOnServiceImpl;
import com.crystalpizaa.api.service.PizzaServiceImpl;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.models.core.AddOnType;
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
@DisplayName("AddOn controller test cases")
@WebMvcTest
public class AddOnControllerTest {
  @Mock
  private AddOnServiceImpl addOnService;

  @InjectMocks
  private AddOnController addOnController;

  private MockMvc mockMvc;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(addOnService.getAll()).thenReturn(ServiceMockData.getAllAddOns());
    when(addOnService.get(anyInt())).thenReturn(ServiceMockData.getAddOnById(1));
    when(addOnService.save(ArgumentMatchers.any())).thenReturn(ServiceMockData.saveAddon());
    when(addOnService.remove(anyInt())).thenReturn(true);
    mockMvc = MockMvcBuilders
        .standaloneSetup(addOnController)
        .build();
  }


  @Test
  @Description("Get all addon controller method test")
  public void getAllTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/addons/get/all")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Get addOn by id controller method test")
  public void getByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/addons/get/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Save AddOn controller method test")
  public void saveTest() throws Exception {
    AddOn addOn = new AddOn();
    addOn.setName("New Peppere pizza");
    addOn.setDescription("New Peppere pizza");
    addOn.setPrice(550);
    addOn.setSize(Size.Medium);
    addOn.setType(AddOnType.Beverage);
    this.mockMvc.perform(MockMvcRequestBuilders.post("/addons/save")
        .content(new ObjectMapper().writeValueAsString(addOn))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Remove AddOn controller method test")
  public void removeTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/addons/remove/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

}
