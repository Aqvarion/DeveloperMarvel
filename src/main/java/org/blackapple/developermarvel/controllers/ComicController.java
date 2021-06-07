package org.blackapple.developermarvel.controllers;

import org.blackapple.developermarvel.entities.Character;
import org.blackapple.developermarvel.entities.Comic;
import org.blackapple.developermarvel.services.ComicService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> create(@RequestPart(name = "comic") Comic comic, @RequestPart(name = "img") MultipartFile img) throws IOException {

        comicService.create(comic, img);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    public ResponseEntity<Map<String, Object>> read(@RequestParam(required = false) String title,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page,size, Sort.Direction.ASC, "title");

        Page<Comic> comicsPage;
        if (title==null)
            comicsPage = comicService.readAll(pageable);
        else
            comicsPage = comicService.readAll(title,pageable);

        List<Comic> comics = comicsPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("comics", comics);
        response.put("currentPage", comicsPage.getNumber());
        response.put("totalItems", comicsPage.getTotalElements());
        response.put("totalPages", comicsPage.getTotalPages());

        return response!=null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
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
    public ResponseEntity<Map<String, Object>> readCharacters(@PathVariable(name = "comicId") Long id,
                                                              @RequestParam(required = false) String name,
                                                              @RequestParam (defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size){

        Pageable pageable = PageRequest.of(page,size, Sort.Direction.ASC,"name");

        Page<Character> charactersPage;
        if (name==null)
            charactersPage = comicService.readCharacters(id, pageable);
        else
            charactersPage = comicService.readCharacters(id,name,pageable);

        List<Character> characters = charactersPage.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("characters", characters);
        response.put("currentPage", charactersPage.getNumber());
        response.put("totalItems", charactersPage.getTotalElements());
        response.put("totalPages", charactersPage.getTotalPages());

        return characters!=null && !characters.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{comicId}")
    public ResponseEntity<?> update(@PathVariable(name = "comicId") Long id, @RequestPart(name = "comic") Comic comic, @RequestPart(name = "img") MultipartFile img) throws IOException {
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
