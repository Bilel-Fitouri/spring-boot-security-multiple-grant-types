package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    //This is a public API
    @GetMapping("/api/ping")
    public String pong(){
        return "pong";
    }

    //This is a secured API
    @GetMapping("/api/pang")
    public String pang(){
        return "pang";
    }


}
