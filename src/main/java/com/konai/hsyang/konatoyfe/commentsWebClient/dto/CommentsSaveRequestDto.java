package com.konai.hsyang.konatoyfe.commentsWebClient.dto;

import com.konai.hsyang.konatoyfe.loginWebClient.domain.User;
import com.konai.hsyang.konatoyfe.postWebClient.domain.Posts;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class CommentsSaveRequestDto {

    private Long commentsID;
    private String content;
    private User user;
    private Posts post;
    private LocalDateTime createdate;
    private LocalDateTime modifieddate;

    public void setCommentInfo(User user, Posts post){
        this.user = user;
        this.post = post;
    }
}
