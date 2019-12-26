package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.interfaces.UserService;
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
  public ResponseEntity<User> GetById(@PathVariable int id)
  {
      return  new ResponseEntity<>(this.userService.GetById(id), HttpStatus.OK);
  }

  @RequestMapping(name = "Get all",value = "/get/all", method = RequestMethod.GET)
  public ResponseEntity<List<User>> Get()
  {
    return  new ResponseEntity<>(this.userService.GetAll(), HttpStatus.OK);
  }


  @RequestMapping(name = "Save",value = "/save", method = RequestMethod.POST)
  public ResponseEntity<User> Save(@RequestBody User user)
  {
    return  new ResponseEntity<>(this.userService.Save(user), HttpStatus.CREATED);
  }

  @RequestMapping(name = "Delete",value = "/remove/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> Remove(@PathVariable int id)
  {
    return  new ResponseEntity<>(this.userService.Remove(id), HttpStatus.OK);
  }
}
