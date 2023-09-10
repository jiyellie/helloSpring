package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")//찾는 곳이 있기에 홈으로 바로 이동
    public String home() {
        return "home";
    }
}
