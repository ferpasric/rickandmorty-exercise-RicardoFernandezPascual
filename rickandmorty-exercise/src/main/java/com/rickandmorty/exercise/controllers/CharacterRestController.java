package com.rickandmorty.exercise.controllers;

import com.rickandmorty.exercise.dtos.CharacterDTO;
import com.rickandmorty.exercise.services.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CharacterRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private CharacterService characterService;

    @Autowired
    public CharacterRestController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping(path = "search-character-appearance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CharacterDTO> searchCharacterAppearance(@RequestParam String name){
        logger.info("Start: Search character: {}", name);
        CharacterDTO characterDTO = characterService.searchCharacterAppearance(name);
        logger.info("End: Search character: {}", characterDTO);
        return new ResponseEntity<>(characterDTO, HttpStatus.OK);
    }

}
