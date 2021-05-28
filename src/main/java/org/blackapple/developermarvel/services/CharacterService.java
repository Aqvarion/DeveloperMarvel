package org.blackapple.developermarvel.services;

import java.util.List;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;

public interface CharacterService {

    void create(Character character);

    List<Character> readAll();

    Character read(Long id);

    boolean update(Character character, int id);

    boolean delete(Long id);

    List<Comic> readComics(int id);
}
