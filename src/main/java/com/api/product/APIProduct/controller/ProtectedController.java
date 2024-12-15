package com.api.product.APIProduct.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedController {


    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is an ADMIN endpoint";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "This is a USER endpoint";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }
}
