package com.crystalpizaa.api.service;

import com.crystalpizaa.api.AppLogger;
import com.crystalpizaa.api.dao.interfaces.AddOnRepository;
import com.crystalpizaa.api.service.models.core.AddOn;
import com.crystalpizaa.api.service.models.validation.DataNotFoundException;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import com.crystalpizaa.api.service.translators.AddOnTranslator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddOnServiceImpl implements AddOnService {

  @Autowired
  private AddOnRepository addOnRepository;


  @Override
  public AddOn save(AddOn addOn) {
    try {
      if (this.EnsureValid(addOn)) {
        com.crystalpizaa.api.dao.entities.AddOn addOnObj = this.addOnRepository
            .save(AddOnTranslator.toDaoModel(addOn));
        return AddOnTranslator.toServiceModel(addOnObj);
      }
    } catch (Exception ex) {
      if (ex instanceof ValidationException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }

    return null;
  }

  @Override
  public List<AddOn> getAll() {
    try {
      List<com.crystalpizaa.api.dao.entities.AddOn> addOns = this.addOnRepository.findAll();
      if (addOns == null || addOns.size() == 0) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails("AddOns not found");
        AppLogger.log(this.getClass().getName(), "AddOns not found", Level.INFO);
        throw new DataNotFoundException(error);
      }
      return AddOnTranslator.toServiceModel(addOns);
    } catch (Exception ex) {
      if (ex instanceof DataNotFoundException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }
    return null;
  }

  @Override
  public AddOn get(int id) {
    try {
      com.crystalpizaa.api.dao.entities.AddOn addOn = this.addOnRepository.findById(id)
          .orElse(null);
      if (addOn == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested Addon of id %d not found", id));
        throw new DataNotFoundException(error);
      }
      return AddOnTranslator.toServiceModel(addOn);
    } catch (Exception ex) {
      if (ex instanceof DataNotFoundException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }
    return null;
  }

  @Override
  public boolean remove(int id) {
    try {
      com.crystalpizaa.api.dao.entities.AddOn addOn = this.addOnRepository.findById(id)
          .orElse(null);
      if (addOn == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested Addon of id %d not found", id));
        throw new DataNotFoundException(error);
      }
      this.addOnRepository.deleteById(id);
      return true;
    } catch (Exception ex) {
      if (ex instanceof DataNotFoundException) {
        throw ex;
      }
      AppLogger
          .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
    }
    return false;
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
