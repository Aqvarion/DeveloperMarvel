package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.repostiroies.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void create(Comic comic) {
        comicRepository.save(comic);
    }

    @Override
    public List<Comic> readAll() {
        return comicRepository.findAll();
    }

    @Override
    public Comic read(Long id) {
        return comicRepository.findById(id).get();
    }

    @Override
    public boolean update(Comic comic, Long id) {
        if(comicRepository.existsById(id)){
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



        return comicRepository.findById(id).get().getCharacters();
    }
}
