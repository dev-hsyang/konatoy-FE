package com.konai.hsyang.konatoyfe.commentsWebClient.service;

import com.konai.hsyang.konatoyfe.commentsWebClient.dto.CommentsResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentsRestClientTest {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    CommentsRestClientService commentsRestClientService = new CommentsRestClientService(webClient);

    @DisplayName("댓글 리스트 가져오기 테스트")
    @Test
    void getCommentsListTest(){

        List<CommentsResponseDto> commentsResponseDtoList = commentsRestClientService.getCommentsList("dev-seung", 9L);

        for(CommentsResponseDto dto : commentsResponseDtoList){
            System.out.println(dto.getContent());
        }

        assertThat(commentsResponseDtoList.get(0).getCommentsID()).isEqualTo(3L);
    }
}
