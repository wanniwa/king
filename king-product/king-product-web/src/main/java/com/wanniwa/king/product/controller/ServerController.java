package com.wanniwa.king.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    @GetMapping("/msg")
    public String getProductMsg() {
        return "product Server msg";
    }

}
