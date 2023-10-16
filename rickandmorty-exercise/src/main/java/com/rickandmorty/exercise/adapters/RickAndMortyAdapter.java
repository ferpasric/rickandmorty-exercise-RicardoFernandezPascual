package com.rickandmorty.exercise.adapters;

import com.rickandmorty.exercise.adapters.dtos.ListRickAndMortyCharacterDTO;
import com.rickandmorty.exercise.adapters.dtos.RickAndMortyCharacterDTO;
import com.rickandmorty.exercise.adapters.dtos.RickAndMortyEpisodeDTO;
import com.rickandmorty.exercise.dtos.CharacterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RickAndMortyAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${rickandmortyapi.character.url:}")
    private String url;

    private RestTemplate restTemplate;

    @Autowired
    public RickAndMortyAdapter(RestTemplate restTemplate){
            this.restTemplate = restTemplate;
    }

    public CharacterDTO searchCharacterAppearance(String name){

        List<RickAndMortyCharacterDTO> rickAndMortyCharacterDTOList = searchRickAndMortyCharacterAppearance(name);

        if(!rickAndMortyCharacterDTOList.isEmpty()) {

            logger.debug("Create character for name: {}", name);
            CharacterDTO characterDTO = new CharacterDTO();

            logger.debug("Set name in characterDTO for name: {}", name);
            characterDTO.setName(rickAndMortyCharacterDTOList.get(0).getName());

            logger.debug("Gets all the characters by name \"{}\" and it get the non-repeated list of the episodes the character are involved in", name);
            HashSet<String> episodesUrlList = new HashSet<>();
            rickAndMortyCharacterDTOList.forEach(r -> episodesUrlList.addAll(r.getEpisodesUrl()));
            List<RickAndMortyEpisodeDTO> rickAndMortyEpisodeDTOList = new ArrayList<>();
            episodesUrlList.forEach(e -> rickAndMortyEpisodeDTOList.add(getEpisodie(e)));

            logger.debug("Sort episode list by air date for name: {}", name);
            Comparator<RickAndMortyEpisodeDTO> rickAndMortyEpisodeDTOComparator = Comparator
                    .comparing(RickAndMortyEpisodeDTO::getAirDate);
            List<RickAndMortyEpisodeDTO> rickAndMortyEpisodeDTOListSorted = rickAndMortyEpisodeDTOList.stream().sorted(rickAndMortyEpisodeDTOComparator).collect(Collectors.toList());

            logger.debug("Set episodies in characterDTO for name: {}", name);
            List<String> episodes = new ArrayList<>();
            rickAndMortyEpisodeDTOListSorted.stream().forEachOrdered(r -> episodes.add(r.getName()));
            characterDTO.setEpisodes(episodes);

            logger.debug("Get firstAppearance in characterDTO for name: {}", name);
            Date firstAppearance = rickAndMortyEpisodeDTOListSorted.get(0).getAirDate();
            characterDTO.setFirstAppearance(firstAppearance);

            return characterDTO;
        }else{
            throw new org.springframework.web.client.HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

    }

    private RickAndMortyEpisodeDTO getEpisodie(String url){

        logger.info("Get episode from API {}", url);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity	= new HttpEntity<>(headers);

        ParameterizedTypeReference<RickAndMortyEpisodeDTO> responseType = new ParameterizedTypeReference<RickAndMortyEpisodeDTO>() {
        };

        ResponseEntity<RickAndMortyEpisodeDTO> response	= restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);

        return response.getBody();
        }

    private List<RickAndMortyCharacterDTO> searchRickAndMortyCharacterAppearance(String name){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity	= new HttpEntity<>(headers);

        ParameterizedTypeReference<ListRickAndMortyCharacterDTO> responseType = new ParameterizedTypeReference<ListRickAndMortyCharacterDTO>() {
        };

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("name", name);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name)
                .encode()
                .toUriString();

        logger.info("Search Rick And Morty Character Appearance from API {}", urlTemplate);

        ResponseEntity<ListRickAndMortyCharacterDTO> response	= restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity, responseType);

        ListRickAndMortyCharacterDTO listRickAndMortyCharacterDTO = response.getBody();

        return listRickAndMortyCharacterDTO.getRickAndMortyCharacterDTOList();
    }

}
