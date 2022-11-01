package com.konai.hsyang.konatoyfe.postWebClient.dto;

import lombok.Getter;

@Getter
public class PageRequestDto {

    private String nickname;
    private String title;

    public void setPageDefault(){

        this.nickname = null;
        this.title = null;
    }
}
