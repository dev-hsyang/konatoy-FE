package com.konai.hsyang.konatoyfe.location.controller;

import com.konai.hsyang.konatoyfe.location.dto.LocationSaveRequestDto;
import com.konai.hsyang.konatoyfe.location.service.LocationRestClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LocationApiController {

    private final LocationRestClientService locationRestClient;

    @PostMapping("/api/location")
    public void location(@RequestBody LocationSaveRequestDto requestDto){

        locationRestClient.save(requestDto);
    }
}
