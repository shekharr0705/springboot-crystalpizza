package com.crystalpizaa.api.service.interfaces;

import com.crystalpizaa.api.service.models.core.User;
import java.util.List;

public interface UserService {

  User Save(User user);

  List<User> GetAll();

  User GetById(int id);

  Boolean Remove(int id);

}
