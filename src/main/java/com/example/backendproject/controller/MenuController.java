package com.example.backendproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// HTML 받아와서 보여주는 컨트롤러
@Controller
public class MenuController {
    @GetMapping("/menu")
    public String getMenuPage() {
        return "display.html";
    }
}