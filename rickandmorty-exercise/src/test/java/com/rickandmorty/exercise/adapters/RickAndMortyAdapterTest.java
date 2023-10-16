package com.rickandmorty.exercise.adapters;

import com.rickandmorty.exercise.adapters.dtos.ListRickAndMortyCharacterDTO;
import com.rickandmorty.exercise.adapters.dtos.RickAndMortyCharacterDTO;
import com.rickandmorty.exercise.adapters.dtos.RickAndMortyEpisodeDTO;
import com.rickandmorty.exercise.dtos.CharacterDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.internal.util.Assert;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class RickAndMortyAdapterTest {



    @Test
    void searchCharacterAppearanceTest() {

        // GIVEN
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        RickAndMortyAdapter rickAndMortyAdapter = new RickAndMortyAdapter(restTemplate);
        String name = "Rick Sanchez";
        Date expectedFirstAppearance = java.util.Date.from(LocalDate.of(2013, 2, 12).atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());

        String url = "http://rickandmortyapi";
        ReflectionTestUtils.setField(rickAndMortyAdapter, "url", url);

        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);

        Mockito.when(restTemplate.exchange(
                Mockito.contains(url), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(
                        ParameterizedTypeReference.class))).thenReturn(responseEntity);

        List<RickAndMortyCharacterDTO> rickAndMortyCharacterDTOList = new ArrayList();
        RickAndMortyCharacterDTO rickAndMortyCharacterDTO =  new RickAndMortyCharacterDTO();
        rickAndMortyCharacterDTO.setId(1L);
        rickAndMortyCharacterDTO.setName(name);

        String episodeUrl1 = "episodeUrl1";
        String episodeUrl2 = "episodeUrl2";

        // Mock to get Character
        List<String> episodesUrl = new ArrayList<>();
        episodesUrl.add(episodeUrl1);
        episodesUrl.add(episodeUrl2);
        rickAndMortyCharacterDTO.setEpisodesUrl(episodesUrl);
        rickAndMortyCharacterDTOList.add(rickAndMortyCharacterDTO);
        ListRickAndMortyCharacterDTO listRickAndMortyCharacterDTO = new ListRickAndMortyCharacterDTO();
        listRickAndMortyCharacterDTO.setRickAndMortyCharacterDTOList(rickAndMortyCharacterDTOList);
        Mockito.when(responseEntity.getBody()).thenReturn(listRickAndMortyCharacterDTO);

        // Mock to get the episodies
        ResponseEntity responseEntityEpisode1 = Mockito.mock(ResponseEntity.class);
        RickAndMortyEpisodeDTO episode1 = new RickAndMortyEpisodeDTO();
        episode1.setName("Pilot");
        episode1.setAirDate(expectedFirstAppearance);
        Mockito.when(restTemplate.exchange(
                Mockito.contains(episodeUrl1), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(
                        ParameterizedTypeReference.class))).thenReturn(responseEntityEpisode1);
        Mockito.when(responseEntityEpisode1.getBody()).thenReturn(episode1);

        ResponseEntity responseEntityEpisode2 = Mockito.mock(ResponseEntity.class);
        RickAndMortyEpisodeDTO episode2 = new RickAndMortyEpisodeDTO();
        episode2.setName("Chapter two");
        episode2.setAirDate(java.util.Date.from(LocalDate.of(2013, 04, 20).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));

        Mockito.when(restTemplate.exchange(
                Mockito.contains(episodeUrl2), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(
                        ParameterizedTypeReference.class))).thenReturn(responseEntityEpisode2);
        Mockito.when(responseEntityEpisode2.getBody()).thenReturn(episode2);

        // WHEN
        CharacterDTO characterDTO = rickAndMortyAdapter.searchCharacterAppearance(name);

        // THEN
        Assert.isTrue(characterDTO.getFirstAppearance().equals(expectedFirstAppearance));
    }

    @Test
    void searchCharacterAppearanceNotFoundTest() {

        // GIVEN
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        RickAndMortyAdapter rickAndMortyAdapter = new RickAndMortyAdapter(restTemplate);
        String name = "Rick Sanchez";

        String url = "http://rickandmortyapi";
        ReflectionTestUtils.setField(rickAndMortyAdapter, "url", url);

        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);

        Mockito.when(restTemplate.exchange(
                Mockito.contains(url), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(
                        ParameterizedTypeReference.class))).thenReturn(responseEntity);

        ListRickAndMortyCharacterDTO listRickAndMortyCharacterDTO = new ListRickAndMortyCharacterDTO();
        Mockito.when(responseEntity.getBody()).thenReturn(listRickAndMortyCharacterDTO);

        // WHEN
        try {
            CharacterDTO characterDTO = rickAndMortyAdapter.searchCharacterAppearance(name);
        }

        catch(org.springframework.web.client.HttpClientErrorException e){
            Assert.isTrue(e.getStatusCode() == HttpStatus.NOT_FOUND);
        }

        //
    }

}