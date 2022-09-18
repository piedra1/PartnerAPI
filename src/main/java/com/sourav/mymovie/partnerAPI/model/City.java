package com.sourav.mymovie.partnerAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class City {

    @JsonProperty
    private String cityName;
    @JsonProperty
    private List<Theatre> theatres;
}
