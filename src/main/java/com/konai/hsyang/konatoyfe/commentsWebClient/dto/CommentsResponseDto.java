package com.konai.hsyang.konatoyfe.commentsWebClient.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentsResponseDto {

    private Long commentsID;
    private Long postID;
    private String nickname;
    private String content;
    private LocalDateTime createdate;
    private LocalDateTime modifieddate;
    private String formatCreateDate;
    private String formatModifiedDate;
    private boolean isWriter;
}
