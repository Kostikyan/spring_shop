package com.spring_shop.service;

import com.spring_shop.entity.Cart;
import com.spring_shop.entity.User;
import com.spring_shop.security.CurrentUser;

import java.util.List;

public interface CartService {
    List<Cart> findAll();

    void save(Cart cart, int id, CurrentUser currentUser);

    List<Cart> findCartByUserId(User user);

    int findCartProductsSum(User user);

    void deleteCartById(int id);

    int countOfProductsInCartByUser(User user);

    boolean deleteAllByUserId(int userId);

}