package com.konai.hsyang.konatoyfe.postWebClient.service;

import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsImageResponseDto;
import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsListResponseDto;
import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsResponseDto;
import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.postWebClient.constant.UriConstant.*;

@Slf4j
@RequiredArgsConstructor
public class PostRestClient {

    private final WebClient webClient;

//    public List<PostsResponseDto> retrievePosts(){
//
//        return webClient.get().uri(GET_POSTS_BY_ID)
//                .retrieve()
//                .bodyToFlux(PostsResponseDto.class)
//                .collectList()
//                .block();
//    }

    public PostsResponseDto retrievePostsById(Long postsID){

        try {
        return webClient.get().uri(POSTS_BY_ID, postsID)
                .retrieve()
                .bodyToMono(PostsResponseDto.class)
                .block();
        }catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in retrievePostsById", e);
            throw e;
        }catch (Exception e){
            log.error("Exception in retrievePostsById", e);
            throw e;
        }
    }

    public Long updatePosts(Long postsID, PostsUpdateRequestDto requestDto){

        try {
            return webClient.post().uri(POSTS_UPDATE_BY_ID, postsID)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in updatePosts", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in updatePosts", e);
            throw e;
        }
    }

    public Long deletePosts(Long postsID){

        try {
            return webClient.post().uri(POSTS_DELETE_BY_ID, postsID)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in deletePosts", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in deletePosts", e);
            throw e;
        }
    }

    public PostsImageResponseDto saveImage(MultipartFile multipartFile){

        try {
            return webClient.post().uri(uriBuilder -> uriBuilder
                            .path(POSTS_UPLOAD_IMAGE)
                            .queryParam("image", multipartFile)
                            .build())
                    .retrieve()
                    .bodyToMono(PostsImageResponseDto.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in saveImage", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id saveImage", e);
            throw e;
        }

    }

    // Page 받아오기
    // @AuthenticationPrincipal parameter 해결하기

}
