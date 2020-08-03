package com.windrain.windrainmain.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/hello")
    public String Hello(){
        return "hello world";
    }
}
