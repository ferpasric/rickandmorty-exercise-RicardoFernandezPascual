package com.rickandmorty.exercise.services;

import com.rickandmorty.exercise.adapters.RickAndMortyAdapter;
import com.rickandmorty.exercise.dtos.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private RickAndMortyAdapter rickAndMortyAdapter;

    @Autowired
    public CharacterService(RickAndMortyAdapter rickAndMortyAdapter) {
        this.rickAndMortyAdapter = rickAndMortyAdapter;
    }

    public CharacterDTO searchCharacterAppearance(String name){
        return rickAndMortyAdapter.searchCharacterAppearance(name);
    }
}
