package org.blackapple.developermarvel.controllers;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/public/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService comicService) {
        this.characterService = comicService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestBody Character character) {
        characterService.create(character);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Character>> read() {
        final List<Character> characters = characterService.readAll();

        return characters != null && !characters.isEmpty()
                ? new ResponseEntity<>(characters, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{characterId}")
    public ResponseEntity<Character> read(@PathVariable(name = "characterId") Long id) {
        final Character character = characterService.read(id);

        return character != null
                ? new ResponseEntity<>(character, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/character/{characterId}/comics")
    public ResponseEntity<List<Comic>> readComics(@PathVariable(name = "characterId") int id){
        final List<Comic> comics = characterService.readComics(id);

        return comics!=null && !comics.isEmpty()
                ? new ResponseEntity<>(comics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<?> update(@PathVariable(name = "characterId") int id, @RequestBody Character character) {
        final boolean updated = characterService.update(character, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<?> delete(@PathVariable(name = "characterId") Long id) {
        final boolean deleted = characterService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
