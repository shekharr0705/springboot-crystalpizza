package com.crystalpizaa.api.dao.interfaces;

import com.crystalpizaa.api.dao.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

}
