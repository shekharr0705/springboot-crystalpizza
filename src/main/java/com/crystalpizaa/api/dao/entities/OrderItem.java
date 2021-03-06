package com.crystalpizaa.api.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {

  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int componentId;

  private ComponentType type;

  private int quantity;

}
