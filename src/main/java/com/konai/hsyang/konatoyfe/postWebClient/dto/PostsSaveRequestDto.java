package com.konai.hsyang.konatoyfe.postWebClient.dto;

import com.konai.hsyang.konatoyfe.locationWebClient.domain.Location;
import com.konai.hsyang.konatoyfe.loginWebClient.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostsSaveRequestDto {

    private String title;
    private Location location;
    private User author;
    private String content;
    private Long hits;
    private Long likes;
    private Double latitude;
    private Double longtitude;

    public void setAuthor(User user){

        this.author = user;
    }

    public void setLocation(Location location){

        this.location = location;
    }
    public void init() {

        this.likes = 0L;
        this.hits = 0L;
    }
}
