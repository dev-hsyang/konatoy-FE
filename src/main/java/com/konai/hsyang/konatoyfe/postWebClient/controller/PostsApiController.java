package com.konai.hsyang.konatoyfe.postWebClient.controller;

import com.konai.hsyang.konatoyfe.config.auth.PrincipalDetails;
import com.konai.hsyang.konatoyfe.locationWebClient.service.LocationRestClientService;
import com.konai.hsyang.konatoyfe.loginWebClient.service.UserRestClientService;
import com.konai.hsyang.konatoyfe.postWebClient.dto.*;
import com.konai.hsyang.konatoyfe.postWebClient.service.PostsRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsRestClientService postsRestClient;
    private final LocationRestClientService locationRestClient;
    private final UserRestClientService userRestClient;

    @PostMapping(value="/api/posts", consumes = "application/json")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails){

        return postsRestClient.save(requestDto, principalDetails.getId());
    }

    @PostMapping("/api/posts/{id}")
    public PostsResponseDto postFindById(@PathVariable Long id){

        return postsRestClient.postsResponseDtoFindById(id);
    }

    @PostMapping("/api/posts/update/{id}")
    public Long updatePost(@RequestBody PostsUpdateRequestDto requestDto, @PathVariable Long id){

        return postsRestClient.updatePosts(id, requestDto);
    }

    @PostMapping("/api/posts/delete/{id}")
    public Long deletePost(@PathVariable Long id){

        return postsRestClient.deletePosts(id);
    }

    @PostMapping
    public PostsImageResponseDto image(@RequestParam("image")MultipartFile multipartFile){

        return postsRestClient.saveImage(multipartFile);
    }

}
