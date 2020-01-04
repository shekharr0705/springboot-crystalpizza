package com.crystalpizaa.api.service;

import com.crystalpizaa.api.AppLogger;
import com.crystalpizaa.api.dao.interfaces.UserRepository;
import com.crystalpizaa.api.service.models.core.User;
import com.crystalpizaa.api.service.models.validation.DataNotFoundException;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import com.crystalpizaa.api.service.translators.UserTranslator;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User save(User user) {
    if (this.EnsureValid(user)) {
      try {
        com.crystalpizaa.api.dao.entities.User daoObj = this.userRepository
            .save(UserTranslator.ToDaoEntiry(user));
        return UserTranslator.toServiceModel(daoObj);
      } catch (Exception ex) {
        if (ex instanceof ValidationException) {
          throw ex;
        }
        AppLogger
            .log(this.getClass().getName(), "Exception occurred:" + ex.getMessage(), Level.ERROR);
      }

    }
    return null;
  }

  @Override
  public List<User> getAll() {
    try {
      List<com.crystalpizaa.api.dao.entities.User> daoObjs = this.userRepository.findAll();
      if (daoObjs == null || daoObjs.size() == 0) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails("Users not found");
        AppLogger.log(this.getClass().getName(), "Users not found", Level.INFO);
        throw new DataNotFoundException(error);
      }
      return UserTranslator.toServiceModel(daoObjs);
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
  public User getById(int id) {
    try {
      com.crystalpizaa.api.dao.entities.User daoObj = this.userRepository.findById(id).orElse(null);
      if (daoObj == null) {
        ErrorInfo error = new ErrorInfo();
        error.setTitle("Data not found");
        error.setDetails(String.format("Requested User of id %d not found", id));
        throw new DataNotFoundException(error);
      }
      return UserTranslator.toServiceModel(daoObj);
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
  public Boolean remove(int id) {
    try {
      com.crystalpizaa.api.dao.entities.User daoObj = this.userRepository.findById(id).orElse(null);
      if (daoObj == null) {
        if (daoObj == null) {
          ErrorInfo error = new ErrorInfo();
          error.setTitle("Data not found");
          error.setDetails(String.format("Requested User of id %d not found", id));
          throw new DataNotFoundException(error);
        }
      }
      this.userRepository.deleteById(id);
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


  private boolean EnsureValid(User user) {

    List<ValidationInfo> failedValidations = new ArrayList<>();

    if (user.getName() == null || user.getName().isBlank()) {
      failedValidations.add(new ValidationInfo("name", "Value Required"));
    }

    if (user.getAddress() == null || user.getAddress().isBlank()) {
      failedValidations.add(new ValidationInfo("address", "Value Required"));
    }

    if (user.getContact() == null || user.getContact().isBlank()) {
      failedValidations.add(new ValidationInfo("contact", "Value Required"));
    }

    if (user.getEmailAddress() == null || user.getEmailAddress().isBlank()) {
      failedValidations.add(new ValidationInfo("emailAddress", "Value Required"));
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
