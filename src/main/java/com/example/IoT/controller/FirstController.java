package com.example.IoT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// first response in java/spring
@Controller
public class FirstController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "in spring";  // return text directly
    }
}
