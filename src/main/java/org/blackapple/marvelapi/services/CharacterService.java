package org.blackapple.marvelapi.services;

import java.io.IOException;

import org.blackapple.marvelapi.entities.Character;
import org.blackapple.marvelapi.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CharacterService {

    void create(Character character, MultipartFile img) throws IOException;

    Page<Character> readAll(Pageable paging);

    Page<Character> readAll(String name, Pageable pageable);

    Character read(Long id);

    boolean update(Character character, MultipartFile img, Long id) throws IOException;

    boolean delete(Long id);

    Page<Comic> readComics(Long id, Pageable pageable);

    Page<Comic> readComics(Long id, String title, Pageable pageable);

}
