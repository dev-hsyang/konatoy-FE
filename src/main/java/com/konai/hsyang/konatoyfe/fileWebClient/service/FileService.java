package com.konai.hsyang.konatoyfe.fileWebClient.service;

import com.konai.hsyang.konatoyfe.fileWebClient.domain.FileUtils;
import com.konai.hsyang.konatoyfe.fileWebClient.dto.FileResponseDto;
import com.konai.hsyang.konatoyfe.fileWebClient.dto.FileSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileUtils fileUtils;
    private final FileRestClientService fileRestClient;

    public void fileInfo(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            String tag;
            while(iterator.hasNext()) {
                tag = iterator.next();
                System.out.println("file tag name : " + tag);
                List<MultipartFile> list = multipartHttpServletRequest.getFiles(tag);
                for(MultipartFile multipartFile : list) {
                    System.out.println("\nstart file information");
                    System.out.println("file name: " + multipartFile.getOriginalFilename());
                    System.out.println("file size: " + multipartFile.getSize());
                    System.out.println("file content type: " + multipartFile.getContentType());
                    System.out.println("end file information \n");
                }
            }
        }
    }

    public void uploadFile(MultipartHttpServletRequest multipartHttpServletRequest, Long postsID) throws Exception {

        List<FileSaveRequestDto> filelist = fileUtils.parseFileInfo(postsID, multipartHttpServletRequest);
        for(FileSaveRequestDto requestDto : filelist) {
            fileRestClient.save(requestDto);
        }
    }

    public void downloadFile(Long fileID, HttpServletResponse response){

        FileResponseDto responseDto = new FileResponseDto(fileRestClient.findById(fileID));
        if(ObjectUtils.isEmpty(responseDto) == false){
            String fileName = responseDto.getOrgFileName();
            try {
                byte[] files = org.apache.commons.io.FileUtils.readFileToByteArray(new File(responseDto.getStorePath()));
                response.setContentType("application/octet-stream");
                response.setContentLength(files.length);
                response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");

                response.getOutputStream().write(files);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }catch (Exception e){
                e.getMessage();
            }
        }
    }
}
