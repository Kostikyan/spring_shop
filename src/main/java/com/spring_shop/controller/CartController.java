package com.spring_shop.controller;

import com.spring_shop.entity.Cart;
import com.spring_shop.security.CurrentUser;
import com.spring_shop.service.impl.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/cart", method = RequestMethod.GET)
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartService;

    @GetMapping
    public String cartPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Cart> cartByUserId = cartService.findCartByUserId(currentUser.getUser());
        int priceSum = cartService.findCartProductsSum(currentUser.getUser());
        modelMap.addAttribute("carts", cartByUserId);
        modelMap.addAttribute("price", priceSum);
        return "cart";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int id, @ModelAttribute Cart cart,
                            @AuthenticationPrincipal CurrentUser currentUser) {

        cartService.save(cart, id, currentUser);
        return "redirect:/";
    }
}
