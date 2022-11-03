package com.konai.hsyang.konatoyfe.comments.dto;

import com.konai.hsyang.konatoyfe.login.domain.User;
import com.konai.hsyang.konatoyfe.posts.domain.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
