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
  private int Id;

  @NotNull
  @Getter
  @Setter
  private String Name;

  @Getter
  @Setter
  private String Description;

  @Getter
  @Setter
  private double Price;

  @Getter
  @Setter
  private PizzaType Type;

  @Getter
  @Setter
  private Size Size;

}
