package com.springboot.service;

import com.springboot.entity.User;

import java.util.List;

public interface IUserService {

    User addUser(User user);

    void deleteUser(String id);

    User modifyUser(User user);

    User findById(String id);

    List<User> findByUser(User user);

}
