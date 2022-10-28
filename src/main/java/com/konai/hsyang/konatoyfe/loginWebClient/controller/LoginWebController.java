package com.konai.hsyang.konatoyfe.loginWebClient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginWebController {

    @GetMapping("/joinForm")
    public String join(){

        return "joinForm";
    }
}
