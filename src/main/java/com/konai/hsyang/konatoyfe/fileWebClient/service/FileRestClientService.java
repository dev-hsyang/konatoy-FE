package com.konai.hsyang.konatoyfe.fileWebClient.service;

import com.konai.hsyang.konatoyfe.fileWebClient.domain.File;
import com.konai.hsyang.konatoyfe.fileWebClient.dto.FileSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.fileWebClient.constants.FileUri.*;
@Slf4j
@RequiredArgsConstructor
@Service
public class FileRestClientService {

    private final WebClient webClient;

    public File save(FileSaveRequestDto requestDto){

        try {
            return webClient.post().uri(FILE_SAVE)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(File.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in file save");
            throw e;
        } catch (Exception e){
            log.error("Exception in file save", e);
            throw e;
        }
    }

    public void deleteFile(Long fileID){

        try {
            webClient.get().uri(FILE_DELETE, fileID)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in deleteFile", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in deleteFile", e);
            throw e;
        }
    }

    public File findById(Long fileID){

        try {
            return webClient.get().uri(FILE_BY_ID, fileID)
                    .retrieve()
                    .bodyToMono(File.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in file findById", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in file findById", e);
            throw e;
        }
    }
}
