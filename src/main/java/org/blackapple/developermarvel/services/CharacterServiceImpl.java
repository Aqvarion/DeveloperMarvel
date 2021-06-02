package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.repostiroies.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Component("CharacterService")
public class CharacterServiceImpl implements CharacterService{

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void create(Character character, MultipartFile img) throws IOException {
        character.setImg(Base64.getEncoder().encode(img.getBytes()));
        characterRepository.save(character);
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
    public boolean update(Character character, Long id) {
        if(characterRepository.existsById(id)){
            character.setId(id);
            characterRepository.save(character);
            return true;
        }

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
    public Set<Comic> readComics(Long id) {

        return characterRepository.findById(id).get().getComics();
    }
}
