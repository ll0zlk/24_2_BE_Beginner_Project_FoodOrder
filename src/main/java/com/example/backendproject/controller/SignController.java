package com.example.backendproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/signupSuccess")
    public String signupSuccessPage() {
        return "signupSuccess";
    }

    @GetMapping("/loginPage")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/menu")
    public String menuPage() {
        return "display.html";
    }
}