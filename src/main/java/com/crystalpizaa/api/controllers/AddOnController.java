package com.crystalpizaa.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.crystalpizaa.api.service.AddOnService;
import com.crystalpizaa.api.service.models.core.AddOn;

@RestController
@RequestMapping(name = "addOns", value = "/addons")
public class AddOnController {

  @Autowired
  private AddOnService addOnService;

  @RequestMapping(name = "GetAll", path = "/get/all", method = RequestMethod.GET)
  public ResponseEntity<List<AddOn>> getAll() {
    return new ResponseEntity<>(this.addOnService.getAll(), HttpStatus.OK);
  }

  @RequestMapping(name = "GetById", path = "/get/{id}", method = RequestMethod.GET)
  public ResponseEntity<AddOn> getById(@PathVariable int id) {
    return new ResponseEntity<>(this.addOnService.get(id),HttpStatus.OK);
  }

  @RequestMapping(name = "save", path = "/save",method = RequestMethod.POST)
  public ResponseEntity<AddOn> save(@RequestBody AddOn addOn) {
    return new ResponseEntity<>(this.addOnService.save(addOn),HttpStatus.CREATED);
  }

  @RequestMapping(name = "remove", path = "/remove/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Boolean> remove(@PathVariable int id) {
    return new ResponseEntity<>(this.addOnService.remove(id),HttpStatus.OK);
  }

}
