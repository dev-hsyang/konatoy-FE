package com.konai.hsyang.konatoyfe.fileWebClient.dto;

import com.konai.hsyang.konatoyfe.fileWebClient.domain.File;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FileResponseDto {

    private Long fileID;
    private Long postID;
    private String orgFileName;
    private String storePath;
    private int fileSize;

    public FileResponseDto(File entity) {
        this.fileID = entity.getFileID();
        this.postID = entity.getPosts().getPostsID();
        this.storePath = entity.getStorepath();
        this.orgFileName = entity.getOrgfilename();
        this.fileSize = Math.round(entity.getFilesize()/1024);
    }
}
