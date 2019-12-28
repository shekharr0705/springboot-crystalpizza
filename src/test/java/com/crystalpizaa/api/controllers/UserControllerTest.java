package com.crystalpizaa.api.controllers;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.crystalpizaa.api.mockdata.ServiceMockData;
import com.crystalpizaa.api.service.AddOnServiceImpl;
import com.crystalpizaa.api.service.UserServiceImpl;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.models.core.AddOnType;
import com.crystalpizaa.api.service.models.core.Size;
import com.crystalpizaa.api.service.models.core.User;
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
@DisplayName("User controller test cases")
@WebMvcTest
public class UserControllerTest {
  @Mock
  private UserServiceImpl userService;

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(userService.getAll()).thenReturn(ServiceMockData.getAllUsers());
    when(userService.getById(anyInt())).thenReturn(ServiceMockData.getUserById(1));
    when(userService.save(ArgumentMatchers.any())).thenReturn(ServiceMockData.saveUser());
    when(userService.remove(anyInt())).thenReturn(true);
    mockMvc = MockMvcBuilders
        .standaloneSetup(userController)
        .build();
  }

  @Test
  @Description("Get all users controller method test")
  public void getAllTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/users/get/all")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Get user by id controller method test")
  public void getByIdTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/users/get/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Save user controller method test")
  public void saveTest() throws Exception {
   User user = new User();
   user.setName("Raj");
    user.setAddress("Pune");
    user.setContact("9876543321");
    user.setEmailAddress("raj-pune@gmail.com");
    this.mockMvc.perform(MockMvcRequestBuilders.post("/users/save")
        .content(new ObjectMapper().writeValueAsString(user))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }

  @Test
  @Description("Remove user controller method test")
  public void removeTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.delete("/users/remove/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
  }
}
