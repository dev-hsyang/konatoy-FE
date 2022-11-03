package com.konai.hsyang.konatoyfe.commentsWebClient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
