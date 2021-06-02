package org.blackapple.developermarvel.services;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.springframework.web.multipart.MultipartFile;

public interface CharacterService {

    void create(Character character, MultipartFile img) throws IOException;

    List<Character> readAll();

    Character read(Long id);

    boolean update(Character character, Long id);

    boolean delete(Long id);

    Set<Comic> readComics(Long id);
}
