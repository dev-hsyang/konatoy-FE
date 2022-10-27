package com.konai.hsyang.konatoyfe.postWebClient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsImageResponseDto {

    private String filename;

    public PostsImageResponseDto(String filename){

        this.filename = filename;
    }

    public void setFilename(String filename){

        this.filename = filename;
    }
}
