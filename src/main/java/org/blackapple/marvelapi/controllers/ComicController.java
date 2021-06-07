package org.blackapple.marvelapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.blackapple.marvelapi.entities.Character;
import org.blackapple.marvelapi.entities.Comic;
import org.blackapple.marvelapi.services.ComicService;
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

@RestController
@RequestMapping(value = "/v1/public/comics")
@Tag(name = "Comic API", description = "Interacting with comic data")
public class ComicController {

    private final ComicService comicService;

    @Autowired
    public ComicController(ComicService comicService) {
        this.comicService = comicService;
    }


    @PostMapping(value = "")
    @Operation(summary = "Adds a new comic to the database", description = "Allows you to add a new comic to the database. The request must contain a part comic with main data, and img part with an image file")
    public ResponseEntity<?> create(@RequestPart(name = "comic") Comic comic,
                                    @RequestPart(name = "img") MultipartFile img) throws IOException {

        comicService.create(comic, img);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "")
    @Operation(summary = "Getting a list of comics", description = "Allows you to get a list of comics, with their properties (id, title, author, published, descript, image)")
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

        return !comics.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{comicId}")
    @Operation(summary = "Getting a comic by id", description = "Allows you to get a specific comic by id")
    public ResponseEntity<Comic> read(@PathVariable(name = "comicId") Long id){
        final Comic comic = comicService.read(id);

        return comic!=null
                ? new ResponseEntity<>(comic, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{comicId}/characters")
    @Operation(summary = "Getting a list of characters of a comic by its ID", description = "Allows you to get a list of characters contained in a specific comic by ID")
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

        return !characters.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{comicId}")
    @Operation(summary = "Modifies an existing comic", description = "Allows you to modify an existing comic by ID. Contains two parts comic and img")
    public ResponseEntity<?> update(@PathVariable(name = "comicId") Long id, @RequestPart(name = "comic") Comic comic, @RequestPart(name = "img") MultipartFile img) throws IOException {
        final boolean updated = comicService.update(comic,img,id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{comicId}")
    @Operation(summary = "Removes existing comic", description = "Removes the existing comic by ID.")
    public ResponseEntity<?> delete(@PathVariable(name = "comicId") Long id){
        final boolean deleted = comicService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
