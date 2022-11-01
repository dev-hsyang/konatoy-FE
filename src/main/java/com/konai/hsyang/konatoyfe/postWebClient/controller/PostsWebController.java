package com.konai.hsyang.konatoyfe.postWebClient.controller;

import com.konai.hsyang.konatoyfe.commentsWebClient.service.CommentsRestClientService;
import com.konai.hsyang.konatoyfe.config.auth.PrincipalDetails;
import com.konai.hsyang.konatoyfe.locationWebClient.service.LocationRestClientService;
import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsResponseDto;
import com.konai.hsyang.konatoyfe.postWebClient.service.PostsRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostsWebController {

    private final PostsRestClientService postsRestClientService;
    private final CommentsRestClientService commentsRestClientService;
    private final LocationRestClientService locationRestClientService;

    @GetMapping("/posts/save")
    public String savePost(){

        return "posts-saveV2";
    }

    @GetMapping("/posts/view/{id}")
    public String viewPost(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        postsRestClientService.updateHits(id);
        PostsResponseDto responseDto = postsRestClientService.postsResponseDtoFindById(id);
        model.addAttribute("post", responseDto);
        model.addAttribute("author", postsRestClientService.isPostAuthor(principalDetails.getId(), responseDto));
        model.addAttribute("comments", commentsRestClientService.getCommentsList(principalDetails.getNickname(), id));
        model.addAttribute("location", locationRestClientService.dtoFindById(responseDto.getLocation().getLocationID()));
        model.addAttribute("filelist", responseDto.getFiles());
        return "posts-view";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePost(@PathVariable Long id, Model model){

        PostsResponseDto responseDto = postsRestClientService.postsResponseDtoFindById(id);
        model.addAttribute("post", responseDto);
        model.addAttribute("filelist", responseDto.getFiles());
        model.addAttribute("location", locationRestClientService.dtoFindById(responseDto.getLocation().getLocationID()));
        return "posts-update";
    }
}
