package com.crystalpizaa.api.service;

import com.crystalpizaa.api.service.models.core.User;
import java.util.List;

public interface UserService {

  User save(User user);

  List<User> getAll();

  User getById(int id);

  Boolean remove(int id);

}
