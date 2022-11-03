package com.konai.hsyang.konatoyfe.location.service;

import com.konai.hsyang.konatoyfe.location.domain.Location;
import com.konai.hsyang.konatoyfe.location.dto.LocationResponseDto;
import com.konai.hsyang.konatoyfe.location.dto.LocationSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static com.konai.hsyang.konatoyfe.location.constant.LocationUri.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationRestClientService {

    private final WebClient webClient;

    public LocationResponseDto dtoFindById(Long id){

        try {
            return webClient.get().uri(LOCATION_DTO_FIND_BY_ID, id)
                    .retrieve()
                    .bodyToMono(LocationResponseDto.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in LOCATION_dtoFindById", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id LOCATION_dtoFindById", e);
            throw e;
        }
    }

    public Location findById(Long id){

        try {
            return webClient.get().uri(LOCATION_FIND_BY_ID, id)
                    .retrieve()
                    .bodyToMono(Location.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getStatusCode(), e.getResponseBodyAsString());
            log.error("Error ResponseException in LOCATION_findById", e);
            throw e;
        } catch (Exception e){
            log.error("Exception id LOCATION_findById", e);
            throw e;
        }
    }

    public Long save(LocationSaveRequestDto requestDto){

        try {
            return webClient.post().uri(LOCATION_SAVE)
                    .bodyValue(requestDto)
                    .retrieve()
                    .bodyToMono(Long.class)
                    .block();
        } catch (WebClientResponseException e){
            log.error("Error Response Code is {} and the response body is {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            log.error("WebClientResponseException in Location-save", e);
            throw e;
        } catch (Exception e){
            log.error("Exception in location-save", e);
            throw e;
        }
    }
}
