package com.konai.hsyang.konatoyfe.fileWebClient.controller;

import com.konai.hsyang.konatoyfe.fileWebClient.service.FileRestClientService;
import com.konai.hsyang.konatoyfe.fileWebClient.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class FileWebController {

    private final FileService fileService;

    @GetMapping("/file/download/{fileID}")
    public void download(@PathVariable Long fileID, HttpServletResponse response){

        fileService.downloadFile(fileID, response);
    }
}