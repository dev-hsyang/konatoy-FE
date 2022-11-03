package com.konai.hsyang.konatoyfe.comments.service;

import com.konai.hsyang.konatoyfe.comments.dto.CommentsResponseDto;
import com.konai.hsyang.konatoyfe.comments.dto.CommentsSaveRequestDto;
import com.konai.hsyang.konatoyfe.login.domain.User;
import com.konai.hsyang.konatoyfe.posts.service.PostsRestClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentsRestClientTest {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    CommentsRestClientService commentsRestClientService = new CommentsRestClientService(webClient);
    PostsRestClientService postsRestClientService = new PostsRestClientService(webClient);
    @DisplayName("댓글 리스트 가져오기 테스트")
    @Test
    void getCommentsListTest(){

        List<CommentsResponseDto> commentsResponseDtoList = commentsRestClientService.getCommentsList("dev-seung", 9L);

        for(CommentsResponseDto dto : commentsResponseDtoList){
            System.out.println(dto.getContent());
        }

        assertThat(commentsResponseDtoList.get(0).getCommentsID()).isEqualTo(3L);
    }

    @DisplayName("댓글 작성 테스트")
    @Test
    void saveCommentTest(){

        String username = "testname";
        Long postID = 76L;
        CommentsSaveRequestDto testComment = CommentsSaveRequestDto.builder()
                .content("test comment")
                .user(new User())
                .post(postsRestClientService.postFindById(postID))
                .build();

        ResponseEntity entity = commentsRestClientService.saveComment(username, postID, testComment);

        assertThat(entity.getStatusCode()).isEqualTo(200);
    }
}
