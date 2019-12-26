package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.UserService;
import com.crystalpizaa.api.service.models.core.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="user controller",value = "/users")
public class UserController {

  @Autowired
  UserService userService;

  @RequestMapping(name = "Get By Id",value = "/get/{id}", method = RequestMethod.GET)
  public ResponseEntity<User> getById(@PathVariable int id)
  {
      return  new ResponseEntity<>(this.userService.getById(id), HttpStatus.OK);
  }

  @RequestMapping(name = "Get all",value = "/get/all", method = RequestMethod.GET)
  public ResponseEntity<List<User>> get()
  {
    return  new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
  }


  @RequestMapping(name = "Save",value = "/save", method = RequestMethod.POST)
  public ResponseEntity<User> save(@RequestBody User user)
  {
    return  new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
  }

  @RequestMapping(name = "Delete",value = "/remove/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> remove(@PathVariable int id)
  {
    return  new ResponseEntity<>(this.userService.remove(id), HttpStatus.OK);
  }
}
