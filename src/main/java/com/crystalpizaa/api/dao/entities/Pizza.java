package com.crystalpizaa.api.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pizza")
public class Pizza {

  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private int id;

  @NotNull
  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private double price;

  @Getter
  @Setter
  private PizzaType type;

  @Getter
  @Setter
  private Size size;

}
