package com.konai.hsyang.konatoyfe.commentsWebClient.service;

import com.konai.hsyang.konatoyfe.commentsWebClient.dto.CommentsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.konai.hsyang.konatoyfe.commentsWebClient.constants.CommentsUri.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentsRestClientService {

    private final WebClient webClient;

    public List<CommentsResponseDto> getCommentsList(String nickname, Long postID){

        try{
            return webClient.post().uri(uriBuilder -> uriBuilder
                            .path(COMMENTS_FIND_LIST)
                            .queryParam("nickname", nickname)
                            .queryParam("postID", postID)
                            .build())
                    .retrieve()
                    .bodyToFlux(CommentsResponseDto.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in getCommentsList", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in getCommentsList", e);
            throw e;
        }
    }
}
