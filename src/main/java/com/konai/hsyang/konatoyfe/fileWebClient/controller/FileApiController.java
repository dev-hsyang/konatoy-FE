package com.konai.hsyang.konatoyfe.fileWebClient.controller;

import com.konai.hsyang.konatoyfe.fileWebClient.service.FileRestClientService;
import com.konai.hsyang.konatoyfe.fileWebClient.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequiredArgsConstructor
@RestController
public class FileApiController {

    private final FileService fileService;
    private final FileRestClientService fileRestClient;

    @PostMapping("/api/file/showInfo")
    public String file(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        fileService.fileInfo(multipartHttpServletRequest);
        return "redirect:/posts/save";
    }

    @PostMapping("/api/file/upload/{postsID}")
    public void uploadFile(@PathVariable Long postsID, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        fileService.uploadFile(multipartHttpServletRequest, postsID);
    }

    @GetMapping("/api/file/delete/{fileID")
    public void deleteFile(@PathVariable Long fileID){

        fileRestClient.deleteFile(fileID);
    }
}
