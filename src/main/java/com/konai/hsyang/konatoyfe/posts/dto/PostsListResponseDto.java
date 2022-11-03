package com.konai.hsyang.konatoyfe.posts.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String userID;
    private LocalDateTime createdate;
    private String formatDate;
    private String clubID;
    private Long hits;
    private Long likes;

}
