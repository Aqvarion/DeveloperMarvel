package org.blackapple.developermarvel.controllers;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/v1/public/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService comicService) {
        this.characterService = comicService;
    }

    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestPart(name = "comic") Character character,@RequestPart(name = "file") MultipartFile img) throws IOException {
        characterService.create(character,img);
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
    public ResponseEntity<Set<Comic>> readComics(@PathVariable(name = "characterId") Long id){
        final Set<Comic> comics = characterService.readComics(id);

        return comics!=null && !comics.isEmpty()
                ? new ResponseEntity<>(comics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{characterId}")
    public ResponseEntity<?> update(@PathVariable(name = "characterId") Long id, @RequestBody Character character) {
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
