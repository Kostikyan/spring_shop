package com.spring_shop.repository;


import com.spring_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findCartsByUser_Id(int id);

    @Transactional
    void deleteALlByUser_Id(int id);

}