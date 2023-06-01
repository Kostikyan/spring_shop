package com.spring_shop.service;

import com.spring_shop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(int id);

    void save(Product product, MultipartFile multipartFile) throws IOException;

    void deleteById(int id);
}