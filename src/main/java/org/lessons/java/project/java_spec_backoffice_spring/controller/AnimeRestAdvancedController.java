package org.lessons.java.project.java_spec_backoffice_spring.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.project.java_spec_backoffice_spring.model.Anime;
import org.lessons.java.project.java_spec_backoffice_spring.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/anime")
public class AnimeRestAdvancedController {

  @Autowired
  private AnimeService animeService;

  @GetMapping
  public List<Anime> index() {

    List<Anime> anime = animeService.findAll();
    return anime;
  }

  @GetMapping("/search")
  public List<Anime> index(@Valid @RequestParam String query) {

    return animeService.findByTitle(query);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Anime> show(@Valid @PathVariable Integer id) {

    Optional<Anime> animeAttempt = animeService.findById(id);

    if (animeAttempt.isEmpty()) {
      return new ResponseEntity<Anime>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Anime>(animeAttempt.get(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Anime> store(@Valid @RequestBody Anime anime) {

    return new ResponseEntity<Anime>(animeService.create(anime), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Anime> update(@Valid @RequestBody Anime anime, @Valid @PathVariable Integer id) {

    if (animeService.findById(id).isEmpty()) {
      return new ResponseEntity<Anime>(HttpStatus.NOT_FOUND);
    }

    anime.setId(id);
    return new ResponseEntity<Anime>(animeService.update(anime), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Anime> delete(@Valid @PathVariable Integer id) {

    if (animeService.findById(id).isEmpty()) {
      return new ResponseEntity<Anime>(HttpStatus.NOT_FOUND);
    }

    animeService.deleteById(id);
    return new ResponseEntity<Anime>(HttpStatus.OK);
  }
}
