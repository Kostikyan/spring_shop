package com.spring_shop.service;


import com.spring_shop.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void save(User user);

    void deleteById(int id);
}