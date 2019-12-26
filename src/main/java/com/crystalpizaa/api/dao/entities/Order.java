package com.crystalpizaa.api.dao.entities;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private int Id;

  private String Address;

  private Double Total;

  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.REFRESH)
  @JoinColumn(name = "user_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

// ManyToMany Mapping Example
//  @ManyToMany(fetch = FetchType.EAGER,
//      cascade = {
//          CascadeType.PERSIST,
//          CascadeType.MERGE
//      })
//  @JoinTable(name = "order_order_item_xref",
//      joinColumns = {@JoinColumn(name = "order_id")},
//      inverseJoinColumns = {@JoinColumn(name = "order_item_id")})


  @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) // Cascade.All used to save child as well
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "order_id", nullable = false)
  private List<OrderItem> OrderItems;

  private LocalDateTime OrderDate;
}
