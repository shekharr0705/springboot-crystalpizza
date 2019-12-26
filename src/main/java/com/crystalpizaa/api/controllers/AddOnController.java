package com.crystalpizaa.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crystalpizaa.api.service.interfaces.AddOnService;
import com.crystalpizaa.api.service.models.core.AddOn;

@RestController
@RequestMapping(name = "addOns", value = "/addons")
public class AddOnController {

  @Autowired
  private AddOnService addOnService;

  @RequestMapping(name = "GetAll", path = "/get/all", method = RequestMethod.GET)
  public ResponseEntity<List<AddOn>> GetAll() {
    return new ResponseEntity<>(this.addOnService.GetAll(), HttpStatus.OK);
  }

  @RequestMapping(name = "GetById", path = "/get/{id}", method = RequestMethod.GET)
  public ResponseEntity<AddOn> GetById(@PathVariable int id) {
    return new ResponseEntity<>(this.addOnService.Get(id),HttpStatus.OK);
  }

  @RequestMapping(name = "save", path = "/save",method = RequestMethod.POST)
  public ResponseEntity<AddOn> Save(@RequestBody AddOn addOn) {
    return new ResponseEntity<>(this.addOnService.Save(addOn),HttpStatus.CREATED);
  }
}
