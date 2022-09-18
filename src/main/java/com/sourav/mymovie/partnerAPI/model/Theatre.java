package com.sourav.mymovie.partnerAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;
@ToString
@EqualsAndHashCode
@Data
public class Theatre {

    @JsonProperty
    private String theatreName;
    @JsonProperty
    private City city;
    @JsonProperty
    private Set<Movie> movies;
}
