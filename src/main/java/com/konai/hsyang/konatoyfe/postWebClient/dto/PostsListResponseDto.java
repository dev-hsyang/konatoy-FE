package com.konai.hsyang.konatoyfe.postWebClient.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
