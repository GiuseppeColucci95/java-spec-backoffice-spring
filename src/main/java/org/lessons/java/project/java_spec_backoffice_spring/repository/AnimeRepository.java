package org.lessons.java.project.java_spec_backoffice_spring.repository;

import java.util.List;

import org.lessons.java.project.java_spec_backoffice_spring.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {

  public List<Anime> findByTitleContainingIgnoreCase(String title);
}
