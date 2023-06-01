package com.spring_shop.controller;

import com.spring_shop.entity.Product;
import com.spring_shop.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductControler {

    private final ProductServiceImpl productService;

    @GetMapping
    public String productsPage() {
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String productSinglePage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Product> byId = productService.findById(id);
        if (byId.isPresent()) {
            modelMap.addAttribute("product", byId.get());
            return "product-single";
        }
        return "index";
    }
}
