package com.konai.hsyang.konatoyfe.fileWebClient.domain;

import com.konai.hsyang.konatoyfe.fileWebClient.dto.FileSaveRequestDto;
import com.konai.hsyang.konatoyfe.postWebClient.service.PostsRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FileUtils {

    private final PostsRestClientService postsRestClient;

    public List<FileSaveRequestDto> parseFileInfo(Long postsID, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        // request가 비어있으면 종료
        if (ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            System.out.println("***************** No Files Detected ********************");
            return null;
        }

        String saveFileName;
        String originalFileExtension;
        String contentType;

        // 파일이 업로드될 폴더명. 파일이 업로드될 때마다 konaToy_upload 폴더 밑에 yyyyMMdd 형식으로 폴더 생성
        ZonedDateTime current = ZonedDateTime.now();
        String path = "D:/konaToy_upload/" + current.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 파일이 업로드될 폴더 지정, 해당 폴더가 존재하지 않으면 폴더 생성
        java.io.File file = new java.io.File(path);
        if (file.exists() == false)
            file.mkdirs();

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
        List<FileSaveRequestDto> fileList = new ArrayList<>();

        while (iterator.hasNext()) {
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
            for (MultipartFile multipartFile : list) {
                if (multipartFile.isEmpty() == false) {
                    // 파일 형식을 확인하고 그에 따라 이미지 확장자를 지정
                    // 파일 확장자를 파일 이름에서 가져오는 방식을 사용하는 방식은 위험 -> 확장자는 쉽게 바꿀 수 있기 때문에 실제 파일 형식과 확장자가 다를 수 있고, 파일의 위변조를 확인할 수 없기 때문에 위험하다.
                    contentType = multipartFile.getContentType(); // 파일 형식을 확인하고
                    if(ObjectUtils.isEmpty(contentType)) { // 그에 따라 파일 확장자를 지정
                        break;
                    } else if(contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if(contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if(contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    } else break;
                    // 서버에 같은 이름의 파일이 있으면 업로드된 파일이 정상적으로 저장되지 않기에 절대 중복되지 않도록 나노초를 이용해서 파일이름 지정
                    saveFileName = Long.toString(System.nanoTime()) + originalFileExtension;

                    // dto 생성
                    FileSaveRequestDto saveRequestDto = FileSaveRequestDto.builder()
                            .posts(postsRestClient.postFindById(postsID))
                            .saveFileName(saveFileName)
                            .orgFileName(multipartFile.getOriginalFilename())
                            .fileSize(multipartFile.getSize())
                            .storePath(path + "/" + saveFileName)
                            .build();

                    // return될 fileList에 dto추가. 추가된 dto list는 DB에 저장
                    fileList.add(saveRequestDto);
                    file = new File(path + "/" + saveFileName);
                    // 업로드된 파일을 새로운 이름으로 바꾸어 지정된 경로에 저장
                    multipartFile.transferTo(file);
                }
            }
        }
        return fileList;
    }
}
