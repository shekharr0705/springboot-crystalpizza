package com.crystalpizaa.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = {"com.crystalpizaa.api.service.*"})
//@EnableJpaRepositories("com.crystalpizaa.api.dao.*")
//@EntityScan(basePackages = {"com.crystalpizaa.api.dao.*"})
// above settings needed if packages are out of com.crystalpizza.api directory

@SpringBootApplication
@EnableSwagger2
public class CrystalPizzaApplication {

  public static void main(String[] args) {
    final Logger logger = LoggerFactory.getLogger(CrystalPizzaApplication.class);
    SpringApplication.run(CrystalPizzaApplication.class, args);
    logger.info("Service has been Started");
  }

}
