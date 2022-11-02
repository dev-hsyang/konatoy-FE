package com.konai.hsyang.konatoyfe.fileWebClient.dto;


import com.konai.hsyang.konatoyfe.postWebClient.domain.Posts;
import lombok.Builder;

public class FileSaveRequestDto {

    private Posts posts;
    private String orgFileName;
    private String saveFileName;
    private String storePath;
    private Long fileSize;

    @Builder
    public FileSaveRequestDto(Posts posts, String orgFileName, String saveFileName, String storePath, Long fileSize){
        this.posts = posts;
        this.orgFileName = orgFileName;
        this.saveFileName = saveFileName;
        this.storePath = storePath;
        this.fileSize = fileSize;
    }
}
