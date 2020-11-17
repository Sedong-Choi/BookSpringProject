package com.sedong.bookspring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    private static final Logger logger=
            LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hi")
    public String hello(){
        logger.info("hello called........");
        System.out.println("in hello");
        return "hello";
    }

    @RequestMapping("/doC")
    public String doC( @ModelAttribute("msg") String msg){
        logger.info("doC called........");
        return "result";
    }

}



