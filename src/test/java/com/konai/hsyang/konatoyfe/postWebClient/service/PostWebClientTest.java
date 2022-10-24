package com.konai.hsyang.konatoyfe.postWebClient.service;

import com.konai.hsyang.konatoyfe.postWebClient.dto.PostsResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostWebClientTest {

    private static final String BASE_URL = "http://localhost:8080";
    private WebClient webClient = WebClient.create(BASE_URL);
    private WebClient.Builder webClientBuild;

    PostWebClient postWebClient = new PostWebClient(webClient);

    @DisplayName("PostsID로 Post 불러오기 테스트")
    @Test
    void retrievePostsByIdTest(){

        PostsResponseDto responseDto = postWebClient.retrievePostsById(1);

        System.out.println("포스트: " + responseDto);
        System.out.println("포스트 작성자 nickname: " + responseDto.getUser().getNickname());
        System.out.println("포스트 내용: " + responseDto.getContent());

        assertThat(responseDto.getUser().getNickname()).isEqualTo("dev-seung");
        assertThat(responseDto.getPostID()).isEqualTo(1);
    }

    @DisplayName("PostsID로 Post 불러오기 실패 테스트")
    @Test
    void retrievePostsById_NotFoundTest(){

        int postsID = -1;

        Assertions.assertThrows(WebClientResponseException.class, ()-> postWebClient.retrievePostsById(postsID));
    }

}
