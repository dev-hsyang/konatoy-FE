package com.konai.hsyang.konatoyfe.commentsWebClient.domain;

import com.konai.hsyang.konatoyfe.loginWebClient.domain.User;
import com.konai.hsyang.konatoyfe.postWebClient.domain.Posts;

public class Comments {

    private Long commentsID;
    private Posts posts;
    private User user;
    private String content;
}
