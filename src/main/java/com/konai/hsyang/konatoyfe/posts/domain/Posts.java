package com.konai.hsyang.konatoyfe.posts.domain;

import com.konai.hsyang.konatoyfe.comments.domain.Comments;
import com.konai.hsyang.konatoyfe.file.domain.File;
import com.konai.hsyang.konatoyfe.location.domain.Location;
import com.konai.hsyang.konatoyfe.login.domain.User;
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
