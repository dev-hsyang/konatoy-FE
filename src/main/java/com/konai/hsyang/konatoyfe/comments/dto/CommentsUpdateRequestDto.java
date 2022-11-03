package com.konai.hsyang.konatoyfe.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsUpdateRequestDto {

    private Long commentsID;
    private String content;
}
