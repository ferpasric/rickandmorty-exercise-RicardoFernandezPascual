package com.rickandmorty.exercise.services;

import org.junit.jupiter.api.Test;
import com.rickandmorty.exercise.adapters.RickAndMortyAdapter;
import com.rickandmorty.exercise.dtos.CharacterDTO;
import org.mockito.Mockito;
import org.modelmapper.internal.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class CharacterServiceTest {
    @Test
    void searchCharacterAppearanceTest() {

        // GIVEN
        RickAndMortyAdapter rickAndMortyAdapterMock = Mockito.mock(RickAndMortyAdapter.class);
        CharacterService characterService = new CharacterService(rickAndMortyAdapterMock);

        String name = "name";

        CharacterDTO expectedDTO = new CharacterDTO();
        expectedDTO.setName(name);
        expectedDTO.setFirstAppearance(new Date());
        List<String> episodies = new ArrayList<>();
        episodies.add("chapter1");
        episodies.add("chapter2");
        expectedDTO.setEpisodes(episodies);

        Mockito.when(rickAndMortyAdapterMock.searchCharacterAppearance(name)).thenReturn(expectedDTO);

        // WHEN
        CharacterDTO dto = characterService.searchCharacterAppearance(name);

        // THEN
        Assert.isTrue(expectedDTO.getName().equals(dto.getName()));
        Assert.isTrue(expectedDTO.getFirstAppearance().equals(dto.getFirstAppearance()));
        Assert.isTrue(expectedDTO.getEpisodes().size()==dto.getEpisodes().size());
        Assert.isTrue(dto.getEpisodes().stream().anyMatch( e -> expectedDTO.getEpisodes().contains(e)));
    }

}