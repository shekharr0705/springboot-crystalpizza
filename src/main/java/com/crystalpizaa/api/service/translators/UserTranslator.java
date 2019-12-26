package com.crystalpizaa.api.service.translators;


import com.crystalpizaa.api.service.models.core.User;
import java.util.ArrayList;
import java.util.List;

public class UserTranslator {

  public static com.crystalpizaa.api.dao.entities.User ToDaoEntiry(User serviceObj) {
    com.crystalpizaa.api.dao.entities.User user = new com.crystalpizaa.api.dao.entities.User();

    user.setName(serviceObj.getName());
    user.setId(serviceObj.getId());
    user.setAddress(serviceObj.getAddress());
    user.setContact(serviceObj.getContact());
    user.setEmailAddress(serviceObj.getEmailAddress());

    return user;
  }

  public static User toServiceModel(com.crystalpizaa.api.dao.entities.User daoObj) {
    User user = new User();

    user.setName(daoObj.getName());
    user.setId(daoObj.getId());
    user.setAddress(daoObj.getAddress());
    user.setContact(daoObj.getContact());
    user.setEmailAddress(daoObj.getEmailAddress());

    return user;
  }

  public static List<User> toServiceModel(List<com.crystalpizaa.api.dao.entities.User> daoObjs) {

    List<User> users = new ArrayList<>();

    for (com.crystalpizaa.api.dao.entities.User daoObj : daoObjs) {

      users.add(toServiceModel(daoObj));
    }

    return users;
  }
}
