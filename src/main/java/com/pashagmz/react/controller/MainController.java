package com.pashagmz.react.controller;

import com.pashagmz.react.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private static final String REDIRECT_TEMPLATE = "redirect:%s";


    @GetMapping("/login")
    public String login(String error, Model model, @AuthenticationPrincipal User user) {
        if (null == user) {
            model.addAttribute("error", error);
            return "/login";
        }

        return String.format(REDIRECT_TEMPLATE, "/");
    }

    @GetMapping("/")
    public String main() {
        return "/index";
    }
}
