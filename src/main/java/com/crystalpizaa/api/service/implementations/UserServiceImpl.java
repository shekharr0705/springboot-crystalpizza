package com.crystalpizaa.api.service.implementations;

import com.crystalpizaa.api.dao.interfaces.UserRepository;
import com.crystalpizaa.api.service.interfaces.UserService;
import com.crystalpizaa.api.service.models.core.User;
import com.crystalpizaa.api.service.models.requestresponse.PriceRequest;
import com.crystalpizaa.api.service.models.validation.ErrorInfo;
import com.crystalpizaa.api.service.models.validation.ValidationException;
import com.crystalpizaa.api.service.models.validation.ValidationInfo;
import com.crystalpizaa.api.service.translators.UserTranslator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User Save(User user) {
    if (this.EnsureValid(user)) {
      com.crystalpizaa.api.dao.entities.User daoObj = this.userRepository
          .save(UserTranslator.ToDaoEntiry(user));
      return UserTranslator.ToServiceModel(daoObj);
    }
    return null;
  }

  @Override
  public List<User> GetAll() {
    List<com.crystalpizaa.api.dao.entities.User> daoObjs = this.userRepository.findAll();
    return UserTranslator.ToServiceModel(daoObjs);
  }

  @Override
  public User GetById(int id) {
    com.crystalpizaa.api.dao.entities.User daoObj = this.userRepository.getOne(id);

    return UserTranslator.ToServiceModel(daoObj);
  }

  @Override
  public Boolean Remove(int id) {
    this.userRepository.deleteById(id);
    return true;
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
