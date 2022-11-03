package com.konai.hsyang.konatoyfe.location.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class LocationSaveRequestDto {

    private Double latitude;
    private Double longtitude;

    @Builder
    public LocationSaveRequestDto(Double lat, Double lng) {
        this.latitude = lat;
        this.longtitude = lng;
    }


}
