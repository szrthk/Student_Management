package com.szrthk.Student.Management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheck {

    @GetMapping("hc")
    public String healthcheckup (){
        return ("System is working fine");
    }
}
