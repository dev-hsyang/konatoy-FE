package com.konai.hsyang.konatoyfe.mypage.service;


import com.konai.hsyang.konatoyfe.login.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.mypage.constant.MypageUri.*;
@Slf4j
@RequiredArgsConstructor
@Service
public class MypageRestClientService {

    private final WebClient webClient;

    public Long updateNickname(Long id, UserUpdateRequestDto requestDto){

        try {
            return webClient.post().uri(UPDATE_NICKNAME, id)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in updateNickname", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id updateNickname", e);
            throw e;
        }
    }

    public Long updatePassword(Long id, UserUpdateRequestDto requestDto){

        try {
            return webClient.post().uri(UPDATE_PASSWORD, id)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in updatePassword", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id updatePassword", e);
            throw e;
        }
    }

    public void deleteUser(Long id){

        try {
            webClient.post().uri(DELETE_USER, id)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in deleteUser", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id deleteUser", e);
            throw e;
        }
    }
}
