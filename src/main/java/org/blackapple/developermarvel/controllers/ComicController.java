package org.blackapple.developermarvel.controllers;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.services.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/v1/public/comics")
public class ComicController {

    private final ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }


    @PostMapping(value = "")
    public ResponseEntity<?> create(@RequestPart(name = "comic") Comic comic, @RequestPart(name = "file") MultipartFile img) throws IOException {

        comicService.create(comic, img);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Comic>> read() {
        final List<Comic> comics = comicService.readAll();

        return comics!=null && !comics.isEmpty()
                ? new ResponseEntity<>(comics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{comicId}")
    public ResponseEntity<Comic> read(@PathVariable(name = "comicId") Long id){
        final Comic comic = comicService.read(id);

        return comic!=null
                ? new ResponseEntity<>(comic, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{comicId}/characters")
    public ResponseEntity<Set<Character>> readCharacters(@PathVariable(name = "comicId") Long id){
        final Set<Character> characters = comicService.readCharacters(id);

        return characters!=null && !characters.isEmpty()
                ? new ResponseEntity<>(characters, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{comicId}")
    public ResponseEntity<?> update(@PathVariable(name = "comicId") Long id, @RequestPart(name = "comic") Comic comic, @RequestPart(name = "file") MultipartFile img) throws IOException {
        final boolean updated = comicService.update(comic,img,id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{comicId}")
    public ResponseEntity<?> delete(@PathVariable(name = "comicId") Long id){
        final boolean deleted = comicService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
