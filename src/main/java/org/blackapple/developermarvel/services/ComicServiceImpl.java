package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.repostiroies.CharacterRepository;
import org.blackapple.developermarvel.repostiroies.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component("ComicService")
public class ComicServiceImpl implements ComicService{

    private final ComicRepository comicRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public ComicServiceImpl(ComicRepository comicRepository, CharacterRepository characterRepository){

        this.comicRepository=comicRepository;
        this.characterRepository=characterRepository;
    }

    @Override
    public void create(Comic comic, MultipartFile img) throws IOException {
        comic.setImg(Base64.getEncoder().encode(img.getBytes()));
        comicRepository.save(comic);
    }

    @Override
    public Page<Comic> readAll(Pageable pageable) {
        return comicRepository.findAll(pageable);
    }

    @Override
    public Page<Comic> readAll(String title, Pageable pageable) {
        return comicRepository.findByTitleContaining(title, pageable);
    }

    @Override
    public Comic read(Long id) {
        if(comicRepository.existsById(id))
            return comicRepository.findById(id).get();
        return null;
    }

    @Override
    public boolean update(Comic comic, MultipartFile img, Long id) throws IOException {
        if(comicRepository.existsById(id)){
            comic.setImg(Base64.getEncoder().encode(img.getBytes()));
            comic.setId(id);
            comicRepository.save(comic);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(comicRepository.existsById(id)){
            comicRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Page<Character> readCharacters(Long id, Pageable pageable) {
        if(comicRepository.findById(id).isPresent())
            return characterRepository.findByComicsId(id, pageable);
        return null;
    }

    @Override
    public Page<Character> readCharacters(Long id, String name, Pageable pageable) {
        if(comicRepository.findById(id).isPresent())
            return characterRepository.findByComicsIdAndNameContaining(id,name,pageable);
        return null;
    }
}
