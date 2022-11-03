package com.konai.hsyang.konatoyfe.comments.service;

import com.konai.hsyang.konatoyfe.comments.dto.CommentsResponseDto;
import com.konai.hsyang.konatoyfe.comments.dto.CommentsSaveRequestDto;
import com.konai.hsyang.konatoyfe.comments.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.konai.hsyang.konatoyfe.comments.constants.CommentsUri.*;

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

    public List<CommentsResponseDto> findAllByUserId(Long userID){

        try {
            return webClient.get().uri(COMMENTS_BY_ID, userID)
                    .retrieve()
                    .bodyToFlux(CommentsResponseDto.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in comments findAllByUserId", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in comments findAllByUserId", e);
            throw e;
        }
    }

    public ResponseEntity<?> saveComment(String username, Long postID, CommentsSaveRequestDto requestDto){

        try {
            return webClient.post().uri(uriBuilder -> uriBuilder
                        .path(COMMENTS_SAVE)
                        .queryParam("username", username)
                        .queryParam("postID", postID)
                        .build())
                    .bodyValue(requestDto)
                    .retrieve()
                    .toEntity(String.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in saveComment", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in saveComment", e);
            throw e;
        }
    }

    public Long deleteComment(Long commentID){

        try {
            return webClient.post().uri(COMMENTS_DELETE, commentID)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in deleteComment", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in deleteComment", e);
            throw e;
        }
    }

    public Long updateComment(Long commentID, CommentsUpdateRequestDto requestDto){

        try {
            return webClient.post().uri(COMMENTS_UPDATE, commentID)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in updateComment", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in updateComment", e);
            throw e;
        }
    }

    public Long findPostByCommentID(Long commentID){

        try {
            return webClient.get().uri(POSTS_BY_COMMENT, commentID)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in findPostByCommentID", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in findPostByCommentID", e);
            throw e;
        }
    }



}
