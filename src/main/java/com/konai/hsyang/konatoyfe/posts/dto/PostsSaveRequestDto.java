package com.konai.hsyang.konatoyfe.posts.dto;

import com.konai.hsyang.konatoyfe.location.domain.Location;
import com.konai.hsyang.konatoyfe.login.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
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
