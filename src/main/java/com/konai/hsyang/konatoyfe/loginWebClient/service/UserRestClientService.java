package com.konai.hsyang.konatoyfe.loginWebClient.service;

import com.konai.hsyang.konatoyfe.loginWebClient.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.loginWebClient.constant.UserUri.USER_FIND_BY_ID;
import static com.konai.hsyang.konatoyfe.loginWebClient.constant.UserUri.USER_FIND_BY_USERNAME;

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

    public User findById(Long userID){

        try {
            return webClient.get().uri(USER_FIND_BY_ID, userID)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
        }  catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in saveImage", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id saveImage", e);
            throw e;
        }
    }
}
