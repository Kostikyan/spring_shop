package com.spring_shop.controller;

import com.spring_shop.entity.Category;
import com.spring_shop.entity.Product;
import com.spring_shop.service.CategoryService;
import com.spring_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping(method = RequestMethod.GET)
public class AdminController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/user/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/add/category/page")
    public String addCategoryPage() {
        return "addCategory";
    }

    @PostMapping("/add/category")
    public String addCategory(@ModelAttribute("product") Category category) {
        categoryService.save(category);
        return "redirect:/user/admin";
    }

    @GetMapping("/add/product/page")
    public String addProductPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "addProduct";
    }

    @PostMapping("/add/product")
    public String addProductForm(@ModelAttribute("product") Product product, @RequestParam("image") MultipartFile multipartFile) {
        try {
            productService.save(product, multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/admin";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteById(id);
        return "redirect:/";
    }
}
