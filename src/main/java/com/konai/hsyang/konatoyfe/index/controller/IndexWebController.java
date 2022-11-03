package com.konai.hsyang.konatoyfe.index.controller;

import com.konai.hsyang.konatoyfe.posts.service.PostsRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class IndexWebController {

    private final PostsRestClientService postsRestClientService;

    @GetMapping("/")
    public String index(){

        return "index";
    }

    @GetMapping("/board")
    public String board(@RequestParam(defaultValue = "date") String sortType, Model model){

        model.addAttribute("posts", postsRestClientService.sort(sortType));
        return "board";
    }

    @GetMapping("/loginForm")
    public String loginForm(){

        return "loginForm";
    }

    @GetMapping("/boardV3")
    public String boardV3(){

        return "boardV3";
    }
}
