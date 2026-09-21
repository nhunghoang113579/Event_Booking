package com.example.eventBooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NominatimResponse {

    private String lat;

    private String lon;

    private String display_name;
}