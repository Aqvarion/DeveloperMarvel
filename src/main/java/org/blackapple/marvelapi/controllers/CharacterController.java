package org.blackapple.marvelapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.blackapple.marvelapi.entities.Character;
import org.blackapple.marvelapi.entities.Comic;
import org.blackapple.marvelapi.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/v1/public/characters")
@Tag(name = "Character API", description = "Interacting with character data")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService comicService) {
        this.characterService = comicService;
    }

    @PostMapping(value = "")
    @Operation(summary = "Adds a new character to the database", description = "Allows you to add a new character to the database. The request must contain a part character with main data, and img part with an image file")
    public ResponseEntity<?> create(@RequestPart(name = "character") Character character,
                                    @RequestPart(name = "img") MultipartFile img) throws IOException {
        characterService.create(character,img);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    @Operation(summary = "Getting a list of characters", description = "Allows you to get a list of characters, with their properties (id, name, biography)")
    public ResponseEntity<Map<String, Object>> read(@RequestParam(required = false) String name,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page,size, Sort.Direction.ASC, "name");

        Page<Character> charactersPage;
        if (name==null)
            charactersPage = characterService.readAll(pageable);
        else
            charactersPage = characterService.readAll(name,pageable);

        List<Character> characters = charactersPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("characters", characters);
        response.put("currentPage", charactersPage.getNumber());
        response.put("totalItems", charactersPage.getTotalElements());
        response.put("totalPages", charactersPage.getTotalPages());

        return !characters.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{characterId}")
    @Operation(summary = "Getting a character by id", description = "Allows you to get a specific character by id")
    public ResponseEntity<Character> read(@PathVariable(name = "characterId") Long id) {
        final Character character = characterService.read(id);

        return character != null
                ? new ResponseEntity<>(character, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{characterId}/comics")
    @Operation(summary = "Getting a list of comics of a character by its ID", description = "Allows you to get a list of comics containing a specific character by ID")
    public ResponseEntity<Map<String, Object>> readComics(@PathVariable(name = "characterId") Long id,
                                                          @RequestParam(required = false) String title,
                                                          @RequestParam (defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size){

        Pageable pageable = PageRequest.of(page,size,Sort.Direction.ASC,"title");

        Page<Comic> comicsPage;
        if (title==null)
            comicsPage = characterService.readComics(id, pageable);
        else
            comicsPage = characterService.readComics(id, title, pageable);

        List<Comic> comics = comicsPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("comics", comics);
        response.put("currentPage", comicsPage.getNumber());
        response.put("totalItems", comicsPage.getTotalElements());
        response.put("totalPages", comicsPage.getTotalPages());

        return !comics.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{characterId}")
    @Operation(summary = "Modifies an existing character", description = "Allows you to modify an existing character by ID. Contains two parts character and img")
    public ResponseEntity<?> update(@PathVariable(name = "characterId") Long id,
                                    @RequestPart(name = "character") Character character,
                                    @RequestPart(name = "img") MultipartFile img) throws IOException {
        final boolean updated = characterService.update(character,img,id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{characterId}")
    @Operation(summary = "Removes existing character", description = "Removes the existing character by ID.")
    public ResponseEntity<?> delete(@PathVariable(name = "characterId") Long id) {
        final boolean deleted = characterService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
