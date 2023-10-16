package com.rickandmorty.exercise.dtos;

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
public class CharacterDTO implements Serializable {

    private String name;

    private List<String> episodes = new ArrayList<>();

    @JsonProperty("first_apppearance")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date firstAppearance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEpisodes() {
       return episodes;
    }

   public void setEpisodes(List<String> episodes) {
        this.episodes = episodes;
    }

    public Date getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(Date firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

    @Override
    public String toString() {
        return "CharacterDTO{" +
                "name='" + name + '\'' +
                ", episodes=" + episodes +
                ", firstAppearance=" + firstAppearance +
                '}';
    }
}
