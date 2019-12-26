package com.crystalpizaa.api.dao.interfaces;

import com.crystalpizaa.api.dao.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
