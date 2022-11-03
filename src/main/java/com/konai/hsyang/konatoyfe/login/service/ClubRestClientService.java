package com.konai.hsyang.konatoyfe.login.service;

import com.konai.hsyang.konatoyfe.login.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.login.constant.UserUri.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClubRestClientService {

    private final WebClient webClient;

    public void setClub(UserJoinRequestDto requestDto, String club){

        try {
             webClient.post().uri(uriBuilder -> uriBuilder
                        .path(USER_CLUB)
                        .queryParam("club", club)
                        .build())
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in setClub", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id setClub", e);
            throw e;
        }
    }
}
