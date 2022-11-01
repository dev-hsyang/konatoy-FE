package com.konai.hsyang.konatoyfe.commentsWebClient.dto;


import java.time.LocalDateTime;

public class CommentsUpdateRequestDto {

    private Long commentsID;
    private String content;
    private LocalDateTime createdate;
    private LocalDateTime modifieddate;
}
