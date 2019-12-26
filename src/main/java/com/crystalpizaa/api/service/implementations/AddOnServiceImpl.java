package com.crystalpizaa.api.service.implementations;

import com.crystalpizaa.api.dao.interfaces.AddOnRepository;
import com.crystalpizaa.api.service.models.core.Pizza;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crystalpizaa.api.service.interfaces.AddOnService;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.translators.AddOnTranslator;

@Service
public class AddOnServiceImpl implements AddOnService {

  @Autowired
  private AddOnRepository addOnRepository;

  @Override
  public AddOn Save(AddOn addOn) {

    if (this.EnsureValid(addOn)) {
      com.crystalpizaa.api.dao.entities.AddOn addOnObj = this.addOnRepository
          .save(AddOnTranslator.ToDaoModel(addOn));
      return AddOnTranslator.ToServiceModel(addOnObj);
    }

    return null;
  }

  @Override
  public List<AddOn> GetAll() {
    List<com.crystalpizaa.api.dao.entities.AddOn> addOns = this.addOnRepository.findAll();
    return AddOnTranslator.ToServiceModel(addOns);
  }

  @Override
  public AddOn Get(int id) {
    com.crystalpizaa.api.dao.entities.AddOn addOn = this.addOnRepository.getOne(id);
    return AddOnTranslator.ToServiceModel(addOn);
  }

  @Override
  public boolean Remove(int id) {
    this.addOnRepository.deleteById(id);
    return true;
  }

  private boolean EnsureValid(AddOn addOn) {

    List<ValidationInfo> failedValidations = new ArrayList<>();

    if (addOn.getName() == null || addOn.getName().isBlank()) {
      failedValidations.add(new ValidationInfo("name", "Value Required"));
    }

    if (addOn.getSize() == null) {
      failedValidations.add(new ValidationInfo("size", "Value Required"));
    }

    if (addOn.getType() == null) {
      failedValidations.add(new ValidationInfo("type", "Value Required"));
    }

    if (addOn.getPrice() < 1) {
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
