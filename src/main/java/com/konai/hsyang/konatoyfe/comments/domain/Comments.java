package com.konai.hsyang.konatoyfe.comments.domain;

import com.konai.hsyang.konatoyfe.login.domain.User;
import com.konai.hsyang.konatoyfe.posts.domain.Posts;

public class Comments {

    private Long commentsID;
    private Posts posts;
    private User user;
    private String content;
}
