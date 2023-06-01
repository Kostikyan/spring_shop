package com.spring_shop.service.impl;

import com.spring_shop.entity.Product;
import com.spring_shop.repository.ProductRepository;
import com.spring_shop.service.ProductService;
import com.spring_shop.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Value("${shop.upload.image.path}")
    private String productImageUploadPath;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product, MultipartFile multipartFile) throws IOException {
        String image = ImageUtil.uploadImage(multipartFile, productImageUploadPath);
        product.setImgPath(image);
        productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

}