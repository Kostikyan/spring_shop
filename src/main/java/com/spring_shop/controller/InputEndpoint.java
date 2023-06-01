package com.spring_shop.controller;

import com.spring_shop.entity.User;
import com.spring_shop.entity.types.UserType;
import com.spring_shop.security.CurrentUser;
import com.spring_shop.service.impl.CartServiceImpl;
import com.spring_shop.service.impl.MainServiceImpl;
import com.spring_shop.service.impl.OrderServiceImpl;
import com.spring_shop.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class InputEndpoint {

    private final MainServiceImpl mainService;
    private final ProductServiceImpl productService;
    private final OrderServiceImpl orderService;
    private final CartServiceImpl cartService;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if(currentUser != null) {
            modelMap.addAttribute("products", productService.findAll());
            modelMap.addAttribute("user", currentUser.getUser());
            modelMap.addAttribute("cartProductsCount", cartService.countOfProductsInCartByUser(currentUser.getUser()));
            modelMap.addAttribute("orderCount", orderService.countOfProductsInOrderByUser(currentUser.getUser()));
            return "index";
        }
        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage")
    public String customLogin() {
        return "customLogin";
    }

    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getType() == UserType.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getType() == UserType.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imageName) throws IOException {
        return mainService.getImage(imageName);
    }

}
