package com.crystalpizaa.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.crystalpizaa.api.dao.interfaces.AddOnRepository;
import com.crystalpizaa.api.mockdata.RepositoryMockData;
import com.crystalpizaa.api.service.AddOnServiceImpl;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.models.core.AddOnType;
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
@DisplayName("Addon service test cases")
public class AddOnServiceImplTest {
  @Mock
  AddOnRepository addOnRepository;

  @InjectMocks
  AddOnServiceImpl addOnService;

  @Before
  @Description("Setting up mock data for test cases")
  public void init() {
    initMocks(this);
    when(addOnRepository.findAll()).thenReturn(RepositoryMockData.getAllAddOns());
    when(addOnRepository.getOne(anyInt())).thenReturn(RepositoryMockData.getAddOnById(1));
    when(addOnRepository.save(ArgumentMatchers.any())).thenReturn(RepositoryMockData.saveAddon());
    doNothing().when(addOnRepository).deleteById(anyInt());
  }

  @Test
  @Description("Get all addOns test")
  public void getAllAddOnsTest() {
    List<AddOn> addOns = addOnService.getAll();

    assertNotNull(addOns);
    assertEquals(2, addOns.size());
  }

  @Test
  @Description("Get addOns by id test")
  public void getAddOnByIdTest() {
    AddOn addOn = this.addOnService.get(1);
    assertNotNull(addOn);
  }


  @Test
  @Description("Save addon test")
  public void saveAddOnTest() {
    AddOn addOn = new AddOn();
    addOn.setId(0);
    addOn.setName("Thumps Up");
    addOn.setDescription("Carbonated water with sugar");
    addOn.setType(AddOnType.Beverage);
    addOn.setSize(Size.Large);
    addOn.setPrice(100);

    AddOn savedAddOn = this.addOnService.save(addOn);
    assertNotNull(savedAddOn);
    assertNotEquals(0, savedAddOn.getId());
  }

  @Test
  @Description("Remove addon test")
  public void removeAddOnTest() {
    assertEquals(true, this.addOnService.remove(1));
  }

  @Test
  @Description("Validation failure | Exception test")
  public void saveAddOnValidationFailTest() {
    Exception exception = new Exception();
    try
    {
      this.addOnService.save(new AddOn());
    } catch (ValidationException ex) {
      exception = ex;
    }

    assertTrue(exception.getClass() == ValidationException.class);
  }
}
