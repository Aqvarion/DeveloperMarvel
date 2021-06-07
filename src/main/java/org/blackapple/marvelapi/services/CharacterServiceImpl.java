package org.blackapple.marvelapi.services;

import org.blackapple.marvelapi.entities.Character;
import org.blackapple.marvelapi.entities.Comic;
import org.blackapple.marvelapi.repostiroies.CharacterRepository;
import org.blackapple.marvelapi.repostiroies.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component("CharacterService")
public class CharacterServiceImpl implements CharacterService{

    private final CharacterRepository characterRepository;
    private final ComicRepository comicRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, ComicRepository comicRepository) {
        this.characterRepository = characterRepository;
        this.comicRepository = comicRepository;
    }

    @Override
    public void create(Character character, MultipartFile img) throws IOException {
        character.setImg(Base64.getEncoder().encode(img.getBytes()));
        characterRepository.save(character);
    }

    @Override
    public Page<Character> readAll(Pageable paging) {

        return characterRepository.findAll(paging);
    }

    @Override
    public Page<Character> readAll(String name, Pageable pageable){
        return characterRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Character read(Long id) {
        if (characterRepository.existsById(id))
            return characterRepository.findById(id).get();
        return null;
    }

    @Override
    public boolean update(Character character, MultipartFile img, Long id) throws IOException {
        if(characterRepository.existsById(id)){
            character.setImg(Base64.getEncoder().encode(img.getBytes()));
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
    public Page<Comic> readComics(Long id, Pageable pageable) {
        if(characterRepository.findById(id).isPresent())
            return comicRepository.findByCharactersId(id, pageable);
        return null;
    }

    @Override
    public Page<Comic> readComics(Long id, String title, Pageable pageable) {
        if(characterRepository.findById(id).isPresent())
            return comicRepository.findByCharactersIdAndTitleContaining(id,title,pageable);
        return null;
    }
}
