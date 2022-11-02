package com.konai.hsyang.konatoyfe.postWebClient.service;

import com.konai.hsyang.konatoyfe.postWebClient.domain.Posts;
import com.konai.hsyang.konatoyfe.postWebClient.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

import static com.konai.hsyang.konatoyfe.postWebClient.constant.PostsUri.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsRestClientService {

    private final WebClient webClient;


//    public List<PostsResponseDto> retrievePosts(){
//
//        return webClient.get().uri(GET_POSTS_BY_ID)
//                .retrieve()
//                .bodyToFlux(PostsResponseDto.class)
//                .collectList()
//                .block();
//    }
//
    public Long save(PostsSaveRequestDto requestDto, Long id){

        return webClient.post().uri(uriBuilder -> uriBuilder
                    .path(POSTS_SAVE)
                    .queryParam("id", id)
                    .build())
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }

    public Posts postFindById(Long postsID){

        try {
            return webClient.get().uri(POSTS_BY_ID, postsID)
                .retrieve()
                .bodyToMono(Posts.class)
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

/********************************** Page 해결해야함 ***********************************/

    public Page page(PageRequestDto requestDto, String param){

        try {
            return webClient.post().uri(uriBuilder -> uriBuilder
                    .path(PAGE_REQUEST)
                    .queryParam("page", param)
                    .build())
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Page.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in page", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in page", e);
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

    public void updateHits(Long id){

        try {
             webClient.get().uri(POSTS_UPDATE_HITS, id)
                     .retrieve()
                     .bodyToMono(Void.class)
                     .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in updateHits", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id updateHits", e);
            throw e;
        }
    }

    public PostsResponseDto postsResponseDtoFindById(Long id){

        try{
            return webClient.get().uri(POSTS_DTO_BY_ID, id)
                    .retrieve()
                    .bodyToMono(PostsResponseDto.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in postsResponseDtoFindById", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id postsResponseDtoFindById", e);
            throw e;
        }
    }

    public Boolean isPostAuthor(Long id, PostsResponseDto responseDto){

        try {
            return webClient.post().uri(POSTS_IS_AUTHOR, id)
                    .bodyValue(responseDto)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in isPostAuthor", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id isPostAuthor", e);
            throw e;
        }
    }

    public List<PostsListResponseDto> sort(String sortType){

        switch (sortType){
            case "date" : return this.findAllDescCurrent();
            case "hits" : return this.findAllDescHits();
            case "likes" : return this.findAllDescLikes();
            default : return  null;
        }
    }

    public void setPostAuthor(PostsSaveRequestDto requestDto, Long userID){

        try{
            webClient.post().uri(uriBuilder -> uriBuilder
                        .path(POSTS_AUTHOR)
                        .queryParam("userID", userID)
                        .build())
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in setPostAuthor", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id setPostAuthor", e);
            throw e;
        }
    }

    public void setLocation(PostsSaveRequestDto requestDto, Long locationID){

        try {
            webClient.post().uri(uriBuilder -> uriBuilder
                    .path(POSTS_LOCATION)
                    .queryParam("locationID", locationID)
                    .build())
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in setLocation", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id setLocation", e);
            throw e;
        }
    }

    public List<PostsListResponseDto> findAllDescCurrent(){

        return webClient.get().uri(POSTS_ALL_DESC_CURRENT)
                .retrieve()
                .bodyToFlux(PostsListResponseDto.class)
                .collectList()
                .block();
    }

    public List<PostsListResponseDto> findAllDescHits(){

        return webClient.get().uri(POSTS_ALL_DESC_HITS)
                .retrieve()
                .bodyToFlux(PostsListResponseDto.class)
                .collectList()
                .block();
    }

    public List<PostsListResponseDto> findAllDescLikes(){

        return webClient.get().uri(POSTS_ALL_DESC_LIKE)
                .retrieve()
                .bodyToFlux(PostsListResponseDto.class)
                .collectList()
                .block();
    }

    public List<PostsListResponseDto> findAllDescId(Long userID){

        return webClient.get().uri(POSTS_ALL_ID, userID)
                .retrieve()
                .bodyToFlux(PostsListResponseDto.class)
                .collectList()
                .block();
    }


}
