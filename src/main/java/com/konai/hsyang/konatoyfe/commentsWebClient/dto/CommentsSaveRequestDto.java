package com.konai.hsyang.konatoyfe.commentsWebClient.dto;

import com.konai.hsyang.konatoyfe.loginWebClient.domain.User;
import com.konai.hsyang.konatoyfe.postWebClient.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CommentsSaveRequestDto {

    private Long commentsID;
    private String content;
    private User user;
    private Posts post;
}
