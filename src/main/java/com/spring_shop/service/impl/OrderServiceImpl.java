package com.spring_shop.service.impl;

import com.spring_shop.entity.Cart;
import com.spring_shop.entity.Order;
import com.spring_shop.entity.Product;
import com.spring_shop.entity.User;
import com.spring_shop.repository.CartRepository;
import com.spring_shop.repository.OrderRepository;
import com.spring_shop.repository.ProductRepository;
import com.spring_shop.security.CurrentUser;
import com.spring_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrderByUserId(User user) {
        return orderRepository.findOrdersByUser_Id(user.getId());
    }

    @Override
    @Transactional
    public void save(List<Cart> carts, Order order, CurrentUser currentUser) {
        order.setDateTime(LocalDateTime.now());
        order.setUser(currentUser.getUser());
        List<Product> products = new ArrayList<>();
        for (Cart cart : carts) {
            products.addAll(cart.getProducts());
        }
        order.setProducts(products);
        orderRepository.save(order);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public int countOfProductsInOrderByUser(User user) {
        return orderRepository.findOrdersByUser_Id(user.getId()).size();
    }

}