package org.blackapple.marvelapi.services;

import org.blackapple.marvelapi.entities.Character;
import org.blackapple.marvelapi.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ComicService {

    void create(Comic comic, MultipartFile file) throws IOException;

    Page<Comic> readAll(Pageable pageable);

    Page<Comic> readAll(String title,Pageable pageable);

    Comic read(Long id);

    boolean update(Comic comic, MultipartFile img, Long id) throws IOException;

    boolean delete(Long id);

    Page<Character> readCharacters(Long id, Pageable pageable);

    Page<Character> readCharacters(Long id, String name, Pageable pageable);

}
