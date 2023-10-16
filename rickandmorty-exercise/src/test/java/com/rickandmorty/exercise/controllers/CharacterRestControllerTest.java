package com.rickandmorty.exercise.controllers;

import com.rickandmorty.exercise.dtos.CharacterDTO;
import com.rickandmorty.exercise.services.CharacterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.internal.util.Assert;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterRestControllerTest {

    @Test
    void searchCharacterAppearanceTest() {

        CharacterService characterService = Mockito.mock(CharacterService.class);

        String name = "name";
        CharacterDTO expectedDTO = new CharacterDTO();
        expectedDTO.setName(name);
        expectedDTO.setFirstAppearance(new Date());
        List<String> episodies = new ArrayList<>();
        episodies.add("chapter1");
        episodies.add("chapter2");
        expectedDTO.setEpisodes(episodies);

        Mockito.when(characterService.searchCharacterAppearance(name)).thenReturn(expectedDTO);

        CharacterRestController characterRestController = new CharacterRestController(characterService);

        // WHEN
        ResponseEntity<CharacterDTO> responseEntity = characterRestController.searchCharacterAppearance(name);

        // THEN
        CharacterDTO dto = responseEntity.getBody();
        Assert.isTrue(expectedDTO.getName().equals(dto.getName()));
        Assert.isTrue(expectedDTO.getFirstAppearance().equals(dto.getFirstAppearance()));
        Assert.isTrue(expectedDTO.getEpisodes().size()==dto.getEpisodes().size());
        Assert.isTrue(dto.getEpisodes().stream().anyMatch( e -> expectedDTO.getEpisodes().contains(e)));


    }

}