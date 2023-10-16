package com.rickandmorty.exercise.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RickAndMortyCharacterDTO implements Serializable {

    private Long id;

    private String name;

    @JsonProperty("episode")
    private List<String> episodesUrl = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEpisodesUrl() {
        return episodesUrl;
    }

    public void setEpisodesUrl(List<String> episodesUrl) {
        this.episodesUrl = episodesUrl;
    }

}
