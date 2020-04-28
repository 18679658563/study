package com.springboot.dao;

import com.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,String> {

}
