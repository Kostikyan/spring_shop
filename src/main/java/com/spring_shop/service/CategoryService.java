package com.spring_shop.service;

import com.spring_shop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    void deleteById(int id);
}