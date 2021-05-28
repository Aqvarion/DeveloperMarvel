package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.repostiroies.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("CharacterService")
public class CharacterServiceImpl implements CharacterService{

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void create(Character character){

    }

    @Override
    public List<Character> readAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character read(Long id) {
        return characterRepository.findById(id).get();
    }

    @Override
    public boolean update(Character character, int id) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(characterRepository.existsById(id)){
            characterRepository.deleteById(id);
            return true;
        } else return false;
    }

    @Override
    public List<Comic> readComics(int id) {
        return null;
    }
}
