package com.spring_shop.controller;

import com.spring_shop.entity.Cart;
import com.spring_shop.entity.Order;
import com.spring_shop.entity.Product;
import com.spring_shop.security.CurrentUser;
import com.spring_shop.service.impl.CartServiceImpl;
import com.spring_shop.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/order", method = RequestMethod.GET)
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final CartServiceImpl cartService;

    @GetMapping
    public String orderPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Order> orders = orderService.findOrderByUserId(currentUser.getUser());
        modelMap.addAttribute("orders", orders);
        return "order";
    }

    @PostMapping("/add-to-order")
    public String addToCart(@ModelAttribute Order order,
                            @AuthenticationPrincipal CurrentUser currentUser) {
        orderService.save(cartService.findCartByUserId(currentUser.getUser()), order, currentUser);
        cartService.deleteAllByUserId(currentUser.getUser().getId());

        return "redirect:/order";
    }
}
