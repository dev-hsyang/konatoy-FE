package com.konai.hsyang.konatoyfe.posts.service;

import com.konai.hsyang.konatoyfe.location.domain.Location;
import com.konai.hsyang.konatoyfe.login.domain.User;
import com.konai.hsyang.konatoyfe.posts.dto.PostsResponseDto;
import com.konai.hsyang.konatoyfe.posts.dto.PostsSaveRequestDto;
import com.konai.hsyang.konatoyfe.posts.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.assertj.core.api.Assertions.assertThat;

public class PostRestClientTest {

    private static final String BASE_URL = "http://localhost:8080";
    private WebClient webClient = WebClient.create(BASE_URL);
//    private WebClient.Builder webClientBuild;

    PostsRestClientService postRestClient = new PostsRestClientService(webClient);

    @DisplayName("PostsID로 Post 불러오기 테스트")
    @Test
    void retrievePostsByIdTest(){

        PostsResponseDto responseDto = postRestClient.postsResponseDtoFindById(1L);

        System.out.println("포스트: " + responseDto);
        System.out.println("포스트 작성자 nickname: " + responseDto.getUser().getNickname());
        System.out.println("포스트 내용: " + responseDto.getContent());

        assertThat(responseDto.getUser().getNickname()).isEqualTo("dev-seung");
        assertThat(responseDto.getPostID()).isEqualTo(1);
    }

    @DisplayName("PostsID로 Post 불러오기 실패 테스트")
    @Test
    void retrievePostsById_NotFoundTest(){

        Long postsID = -1L;

        Assertions.assertThrows(WebClientResponseException.class, ()-> postRestClient.postsResponseDtoFindById(postsID));
    }

    @DisplayName("PostsID로 Post 수정하기 테스트")
    @Transactional
    @Test
    void updatePostsTest(){

        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(null, 1L, "수정 테스트 타이틀3", "수정 테스트 본문3", 37, 127);

        Long postsID = postRestClient.updatePosts(75L, requestDto);

        assertThat(postsID).isEqualTo(75L);
        assertThat(postRestClient.postsResponseDtoFindById(75L).getContent()).isEqualTo("수정 테스트 본문3");
    }

    @DisplayName("PostsID로 Post 수정하기 실패 테스트")
    @Transactional
    @Test
    void updatePostsTest_BadRequest(){

        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(null, null, "수정 테스트 타이틀1", "수정 테스트 본문1", 37, 127);

        Assertions.assertThrows(WebClientResponseException.class, ()-> postRestClient.updatePosts(75L, requestDto));
    }

    @DisplayName("PostsID로 Post 삭제하기 테스트")
    @Test
    void deletePostsTest(){

        Long postsID = 72L;

        Long deleteID = postRestClient.deletePosts(postsID);

        Assertions.assertThrows(WebClientResponseException.class, ()-> postRestClient.postsResponseDtoFindById(postsID));
    }

    @DisplayName("PostsID로 Post 삭제하기 실패 테스트")
    @Test
    void deletePostsTest_BadRequest(){

        Long postsID = 72L;

        Assertions.assertThrows(WebClientResponseException.class, ()-> postRestClient.deletePosts(postsID));
    }

    @DisplayName("Posts 저장 테스트")
    @Test
    void saveTest(){

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title("Test title")
                .location(new Location())
                .author(new User())
                .content("Test content")
                .latitude(10D)
                .longtitude(10D)
                .build();

        Long saveID = postRestClient.save(requestDto, 44L);

        assertThat(saveID).isEqualTo(76L);
        assertThat(postRestClient.postsResponseDtoFindById(saveID).getTitle()).isEqualTo("Test title");
    }
}
