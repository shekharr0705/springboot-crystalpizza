package com.crystalpizaa.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.crystalpizaa.api.dao.interfaces.UserRepository;
import com.crystalpizaa.api.mockdata.RepositoryMockData;
import com.crystalpizaa.api.service.UserServiceImpl;
import com.crystalpizaa.api.service.models.core.User;
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
@DisplayName("User service test cases")
public class UserServiceImplTest {
  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(userRepository.findAll()).thenReturn(RepositoryMockData.getAllUsers());
    when(userRepository.getOne(anyInt())).thenReturn(RepositoryMockData.getUserById(1));
    when(userRepository.save(ArgumentMatchers.any())).thenReturn(RepositoryMockData.saveUser());
    doNothing().when(userRepository).deleteById(anyInt());
  }

  @Test
  @Description("Get all users test")
  public void getAllUsersTest() {
    List<User> users = userService.getAll();

    assertNotNull(users);
    assertEquals(2, users.size());
  }

  @Test
  @Description("Get user by id test")
  public void getUserByIdTest() {
    User user = this.userService.getById(1);
    assertNotNull(user);
  }


  @Test
  @Description("Save user test")
  public void saveUserTest() {
    User user = new User();
    user.setName("Raj");
    user.setEmailAddress("raj@gmail.com");
    user.setAddress("Pune");
    user.setContact("8888085451");

    User savedUser = this.userService.save(user);
    assertNotNull(savedUser);
    assertNotEquals(0, savedUser.getId());
  }

  @Test
  @Description("Remove user test")
  public void removeUserTest() {
    assertEquals(true, this.userService.remove(1));
  }

  @Test
  @Description("Validation failure | Exception test ")
  public void saveAddOnValidationFailTest() {
    Exception exception = new Exception();
    try
    {
      this.userService.save(new User());
    } catch (ValidationException ex) {
      exception = ex;
    }

    assertTrue(exception.getClass() == ValidationException.class);
  }
}
