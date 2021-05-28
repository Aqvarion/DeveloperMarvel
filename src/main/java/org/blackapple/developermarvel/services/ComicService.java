package org.blackapple.developermarvel.services;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;

import java.util.List;
import java.util.Set;

public interface ComicService {

    void create(Comic comic);

    List<Comic> readAll();

    Comic read(Long id);

    boolean update(Comic comic, Long id);

    boolean delete(Long id);

    Set<Character> readCharacters(Long id);
}
