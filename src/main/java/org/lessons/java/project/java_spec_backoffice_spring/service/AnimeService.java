package org.lessons.java.project.java_spec_backoffice_spring.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.project.java_spec_backoffice_spring.model.Anime;
import org.lessons.java.project.java_spec_backoffice_spring.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

  @Autowired
  private AnimeRepository animeRepository;

  public List<Anime> findAll() {
    return animeRepository.findAll();
  }

  public Anime getById(Integer id) {
    return animeRepository.findById(id).get();
  }

  public Optional<Anime> findById(Integer id) {
    return animeRepository.findById(id);
  }

  public List<Anime> findByTitle(String title) {

    return animeRepository.findByTitleContainingIgnoreCase(title);
  }

  public Anime create(Anime anime) {
    return animeRepository.save(anime);
  }

  public Anime update(Anime anime) {
    return animeRepository.save(anime);
  }

  public void deleteById(Integer id) {
    Anime animeToDelete = getById(id);

    animeRepository.delete(animeToDelete);
  }

}
