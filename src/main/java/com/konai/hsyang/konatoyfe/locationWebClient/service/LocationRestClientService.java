package com.konai.hsyang.konatoyfe.locationWebClient.service;

import com.konai.hsyang.konatoyfe.locationWebClient.constant.LocationUri;
import com.konai.hsyang.konatoyfe.locationWebClient.dto.LocationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.locationWebClient.constant.LocationUri.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationRestClientService {

    private final WebClient webClient;

    public LocationResponseDto findByID(Long id){

        try {
            return webClient.get().uri(LOCATION_FIND_BY_ID, id)
                    .retrieve()
                    .bodyToMono(LocationResponseDto.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the reponse body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in LOCATION_findByID", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id LOCATION_findByID");
            throw e;
        }
    }
}
