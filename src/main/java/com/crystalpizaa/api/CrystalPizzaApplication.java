package com.crystalpizaa.api;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = {"com.crystalpizaa.api.service.*"})
//@EnableJpaRepositories("com.crystalpizaa.api.dao.*")
//@EntityScan(basePackages = {"com.crystalpizaa.api.dao.*"})
// above settings needed if packages are out of com.crystalpizza.api directory

@SpringBootApplication
@EnableSwagger2
public class CrystalPizzaApplication {

  public static void main(String[] args) {
    try {
      SpringApplication.run(CrystalPizzaApplication.class, args);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }

  }

}
