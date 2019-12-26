package com.crystalpizaa.api.dao.interfaces;

import com.crystalpizaa.api.dao.entities.AddOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Integer> {

}
