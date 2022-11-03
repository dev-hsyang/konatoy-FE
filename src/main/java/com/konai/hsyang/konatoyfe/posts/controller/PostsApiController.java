package com.konai.hsyang.konatoyfe.posts.controller;

import com.konai.hsyang.konatoyfe.config.auth.PrincipalDetails;
import com.konai.hsyang.konatoyfe.location.dto.LocationSaveRequestDto;
import com.konai.hsyang.konatoyfe.location.service.LocationRestClientService;
import com.konai.hsyang.konatoyfe.login.service.UserRestClientService;
import com.konai.hsyang.konatoyfe.posts.dto.*;
import com.konai.hsyang.konatoyfe.posts.service.PostsRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

        postsRestClient.setPostAuthor(requestDto, principalDetails.getId());
        postsRestClient.setLocation(requestDto, locationRestClient.save(new LocationSaveRequestDto(requestDto.getLatitude(), requestDto.getLongtitude())));
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

    @PostMapping("/api/posts/paging")
    public ResponseEntity<String> page(@RequestBody PageRequestDto requestDto, @RequestParam(required = false, name = "page") Long page){

        // /api/posts/paging?page=pageindex&size=15&sort=createdate
        return postsRestClient.page(requestDto, page);
    }

    @PostMapping("/api/posts/image")
    public PostsImageResponseDto image(@RequestParam("image")MultipartFile multipartFile){

        return postsRestClient.saveImage(multipartFile);
    }
}


