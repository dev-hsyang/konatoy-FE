package com.konai.hsyang.konatoyfe.postWebClient.service;

import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsResponseDto;
import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public PostsResponseDto retrievePostsById(int postsID){

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

    public Long updatePosts(int postsID, PostsUpdateRequestDto requestDto){

        try {
            return webClient.post().uri(POSTS_UPDATE_BY_ID, postsID)
                    .syncBody(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException is addNewEmployee", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in addNewEmployee", e);
            throw e;
        }
    }
}
