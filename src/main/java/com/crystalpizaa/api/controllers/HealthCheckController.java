package com.crystalpizaa.api.controllers;

import com.crystalpizaa.api.service.models.core.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="Healthcheck controller",value = "/health")
public class HealthCheckController {

  @RequestMapping(name = "Check Health",value = "/check", method = RequestMethod.GET)
  public ResponseEntity<String> check()
  {
    return  new ResponseEntity<>("Services are up and Running", HttpStatus.OK);
  }

}
