package com.konai.hsyang.konatoyfe.loginWebClient.service;

import com.konai.hsyang.konatoyfe.loginWebClient.dto.User;
import com.konai.hsyang.konatoyfe.loginWebClient.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.loginWebClient.constant.UserUri.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserRestClientService {

    private final WebClient webClient;

    public User findByUsername(String username){

        try {
            return webClient.get().uri(USER_FIND_BY_USERNAME, username)
                    .retrieve()
                    .bodyToMono(User.class)
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

//    public User findById(Long userID){
//
//        try {
//            return webClient.get().uri(USER_FIND_BY_ID, userID)
//                    .retrieve()
//                    .bodyToMono(User.class)
//                    .block();
//        }  catch (WebClientResponseException e){
//            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
//            log.error("WebClientResponseException in saveImage", e);
//            throw e;
//        } catch (Exception e){
//            log.error("Exception id saveImage", e);
//            throw e;
//        }
//    }

    public Long join(UserJoinRequestDto requestDto){

        try {
            return webClient.post().uri(USER_JOIN)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in user join", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id user join", e);
            throw e;
        }
    }

    public int validateUsername(String username){

        try {
            return webClient.get().uri(uriBuilder -> uriBuilder
                        .path(USER_ID_VALIDATE)
                        .queryParam("username", username)
                        .build())
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException is validateUsername", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id validateUsername", e);
            throw e;
        }
    }

    public int validateNickname(String nickname){

        try {
            return webClient.get().uri(uriBuilder -> uriBuilder
                        .path(USER_NICKNAME_VALIDATE)
                        .queryParam("nickname", nickname)
                        .build())
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException is validateUsername", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id validateUsername", e);
            throw e;
        }
    }
}
