package com.konai.hsyang.konatoyfe.location.service;

import com.konai.hsyang.konatoyfe.location.dto.LocationResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationRestClientTest {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    LocationRestClientService locationRestClientService = new LocationRestClientService(webClient);

    @DisplayName("위치정보 불러오기 테스트")
    @Test
    void findByIdTest(){

        Long locationID = 3L;

        LocationResponseDto responseDto = locationRestClientService.dtoFindById(locationID);

        assertThat(responseDto.getLatitude()).isEqualTo(40L);
    }

}
