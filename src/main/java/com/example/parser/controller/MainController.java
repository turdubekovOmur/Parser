package com.example.parser.controller;

import com.example.parser.service.CustomerUpdateService;
import com.example.parser.service.ParseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MainController {


    private final ParseService parseService;
    private final CustomerUpdateService updateService;

//    @GetMapping("/test")
//    public void test(){
//        parseService.parse("C:\\Users\\u_turdubekov\\Downloads\\Video.xlsx");
//    }


    @GetMapping("/update")
    private Boolean update(@RequestParam Long rbsId){
        return updateService.changeFields(rbsId);
    }

}