package com.rickandmorty.exercise.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListRickAndMortyCharacterDTO {

    @JsonProperty("results")
    private List<RickAndMortyCharacterDTO> rickAndMortyCharacterDTOList;

    public ListRickAndMortyCharacterDTO() {
        rickAndMortyCharacterDTOList = new ArrayList<>();
    }

    public List<RickAndMortyCharacterDTO> getRickAndMortyCharacterDTOList() {
        return rickAndMortyCharacterDTOList;
    }

    public void setRickAndMortyCharacterDTOList(List<RickAndMortyCharacterDTO> rickAndMortyCharacterDTOList) {
        this.rickAndMortyCharacterDTOList = rickAndMortyCharacterDTOList;
    }
}
