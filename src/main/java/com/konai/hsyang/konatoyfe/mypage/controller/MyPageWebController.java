package com.konai.hsyang.konatoyfe.mypage.controller;


import com.konai.hsyang.konatoyfe.comments.service.CommentsRestClientService;
import com.konai.hsyang.konatoyfe.config.auth.PrincipalDetails;
import com.konai.hsyang.konatoyfe.login.service.UserRestClientService;
import com.konai.hsyang.konatoyfe.posts.service.PostsRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MyPageWebController {

    private final PostsRestClientService postsRestClient;
    private final UserRestClientService userRestClient;
    private final CommentsRestClientService commentsRestClient;

    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        model.addAttribute("nickname", principalDetails.getNickname());
        return "mypage";
    }

    @GetMapping("/mypage/posts")
    public String myPosts(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        model.addAttribute("userPosts", postsRestClient.findAllDescId(principalDetails.getId()));
        model.addAttribute("nickname", principalDetails.getNickname());
        return "mypage-posts";
    }

    @GetMapping("/mypage/comments")
    public String myComments(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        model.addAttribute("userComments", commentsRestClient.findAllByUserId(principalDetails.getId()));
        model.addAttribute("nickname", principalDetails.getNickname());
        return "mypage-comments";
    }

    @GetMapping("/mypage/update")
    public String updateMe(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        model.addAttribute("user", userRestClient.findByUsername(principalDetails.getUsername()));
        return "mypage-update";
    }

    @GetMapping("/mypage/delete")
    public String deleteMe(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        model.addAttribute("userID", principalDetails.getId());
        return "mypage-delete";
    }

}
