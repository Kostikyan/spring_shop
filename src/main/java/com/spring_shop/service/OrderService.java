package com.spring_shop.service;

import com.spring_shop.entity.Cart;
import com.spring_shop.entity.Order;
import com.spring_shop.entity.User;
import com.spring_shop.security.CurrentUser;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    List<Order> findOrderByUserId(User user);

    void save(List<Cart> carts, Order order, CurrentUser currentUser);

    int countOfProductsInOrderByUser(User user);
}