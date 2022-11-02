package com.konai.hsyang.konatoyfe.postWebClient.domain;

import com.konai.hsyang.konatoyfe.commentsWebClient.domain.Comments;
import com.konai.hsyang.konatoyfe.fileWebClient.domain.File;
import com.konai.hsyang.konatoyfe.locationWebClient.domain.Location;
import com.konai.hsyang.konatoyfe.loginWebClient.domain.User;
import lombok.Getter;

import java.util.List;

@Getter
public class Posts {

    private Long postsID;
    private User user;
    private Location location;
    private List<Comments> comments;
    private List<File> file;
    private String title;
    private String content;
    private Long hits;
    private Long likes;

}
