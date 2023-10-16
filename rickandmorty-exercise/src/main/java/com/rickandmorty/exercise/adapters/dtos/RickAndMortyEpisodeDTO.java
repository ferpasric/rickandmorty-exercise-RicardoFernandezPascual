package com.rickandmorty.exercise.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Locale;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RickAndMortyEpisodeDTO {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMMM d, yyyy", locale = "en")
    @JsonProperty("air_date")
    private Date airDate;


    //@JsonProperty("air_date")
   // private String airDate;

   // private Date firstAppearance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

}
