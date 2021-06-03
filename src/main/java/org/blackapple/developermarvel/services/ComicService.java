package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ComicService {

    void create(Comic comic, MultipartFile file) throws IOException;

    List<Comic> readAll();

    Comic read(Long id);

    boolean update(Comic comic, MultipartFile img, Long id) throws IOException;

    boolean delete(Long id);

    Set<Character> readCharacters(Long id);

}
