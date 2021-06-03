package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.repostiroies.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Component("ComicService")
public class ComicServiceImpl implements ComicService{

    private final ComicRepository comicRepository;

    @Autowired
    public ComicServiceImpl(ComicRepository comicRepository){
        this.comicRepository=comicRepository;
    }

    @Override
    public void create(Comic comic, MultipartFile img) throws IOException {
        comic.setImg(Base64.getEncoder().encode(img.getBytes()));
        comicRepository.save(comic);
    }

    @Override
    public List<Comic> readAll() {
        return comicRepository.findAll();
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
    public Set<Character> readCharacters(Long id) {
        if(comicRepository.findById(id).isPresent())
            return comicRepository.findById(id).get().getCharacters();
        return null;
    }
}
