package com.konai.hsyang.konatoyfe.file.domain;

import com.konai.hsyang.konatoyfe.posts.domain.Posts;
import lombok.Getter;

@Getter
public class File {

    private Long fileID;
    private Posts posts;
    private String orgfilename;
    private String savefilename;
    private String storepath;
    private Long filesize;
}
