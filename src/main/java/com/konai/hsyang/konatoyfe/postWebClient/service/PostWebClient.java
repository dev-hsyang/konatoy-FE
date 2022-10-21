package com.konai.hsyang.konatoyfe.postWebClient.service;

import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.konai.hsyang.konatoyfe.postWebClient.constant.UriConstant.GET_POSTS;

@RequiredArgsConstructor
public class PostWebClient {

    final WebClient.Builder webClientBuild;
    WebClient webClient = webClientBuild.baseUrl("http://localhost:8080").build();

    public List<PostsResponseDto> retrievePosts(){

        return webClient.get().uri(GET_POSTS)
                .retrieve()
                .bodyToFlux(PostsResponseDto.class)
                .collectList()
                .block();
    }
}
