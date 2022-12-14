package com.konai.hsyang.konatoyfe.login.service;

import com.konai.hsyang.konatoyfe.login.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRestClientTest {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    UserRestClientService userRestClientService = new UserRestClientService(webClient);

    @DisplayName("Username으로 User 찾기 테스트")
    @Test
    void findUserByUsernameTest(){

        String username = "didgsutmd1";

        User testuser = userRestClientService.findByUsername(username);

        assertThat(testuser.getUsername()).isEqualTo(username);
    }

    @DisplayName("Username 중복 확인 테스트")
    @Test
    void validateUsernameTest(){

        String username = "didgsutmd3";

        int validation = userRestClientService.validateUsername(username);

        assertThat(validation).isEqualTo(1);
    }
}
