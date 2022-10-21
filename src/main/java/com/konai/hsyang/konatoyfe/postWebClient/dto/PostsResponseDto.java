package com.konai.hsyang.konatoyfe.postWebClient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsResponseDto {

    private Long postID;
    private User user;
    private Location location;
    private String club;
    private List<CommentsResponseDto> comments;
    private List<FileResponseDto> files;
    private String title;
    private String content;
    private Long hits;
    private Long likes;
    private LocalDateTime createdate;
    private LocalDateTime modifieddate;
    private String formatCreatedate;
    private String formatModifiedDate;
}
