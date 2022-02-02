package com.example.parser.controller;

import com.example.parser.service.ParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private ParseService service;

    @GetMapping("/test")
    public void test(){
        service.parse("C:\\Users\\u_turdubekov\\Downloads\\Video.xlsx");
    }
}