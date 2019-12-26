package com.crystalpizaa.api.dao.interfaces;

import com.crystalpizaa.api.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
