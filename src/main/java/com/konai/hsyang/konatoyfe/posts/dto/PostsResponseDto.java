package com.konai.hsyang.konatoyfe.posts.dto;

import com.konai.hsyang.konatoyfe.comments.dto.CommentsResponseDto;
import com.konai.hsyang.konatoyfe.file.dto.FileResponseDto;
import com.konai.hsyang.konatoyfe.location.domain.Location;
import com.konai.hsyang.konatoyfe.login.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String formatCreateDate;
    private String formatModifiedDate;
}
