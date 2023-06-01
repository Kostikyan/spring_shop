package com.spring_shop.service.impl;

import com.spring_shop.entity.Cart;
import com.spring_shop.entity.Product;
import com.spring_shop.entity.User;
import com.spring_shop.repository.CartRepository;
import com.spring_shop.repository.ProductRepository;
import com.spring_shop.security.CurrentUser;
import com.spring_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> findCartByUserId(User user) {
        return cartRepository.findCartsByUser_Id(user.getId());
    }

    @Override
    public int findCartProductsSum(User user) {
        int sum = 0;
        List<Cart> cartByUserId = findCartByUserId(user);
        for (Cart cart : cartByUserId) {
            for (Product product : cart.getProducts()) {
                sum += product.getPrice();
            }
        }
        return sum;
    }

    @Override
    public void save(Cart cart, int id, CurrentUser currentUser) {
        List<Product> allById = productRepository.findAllById(Collections.singleton(id));
        cart.setProducts(allById);
        cart.setUser(currentUser.getUser());
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartById(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public int countOfProductsInCartByUser(User user) {
        return cartRepository.findCartsByUser_Id(user.getId()).size();
    }

    @Override
    public boolean deleteAllByUserId(int userId) {
        cartRepository.deleteALlByUser_Id(userId);
        return true;
    }

}