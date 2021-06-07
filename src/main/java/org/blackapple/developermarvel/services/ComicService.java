package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ComicService {

    void create(Comic comic, MultipartFile file) throws IOException;

    Page<Comic> readAll(Pageable pageable);

    Comic read(Long id);

    boolean update(Comic comic, MultipartFile img, Long id) throws IOException;

    boolean delete(Long id);

    Page<Character> readCharacters(Long id, Pageable pageable);

}
