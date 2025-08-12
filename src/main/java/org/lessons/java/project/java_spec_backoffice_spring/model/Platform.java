package org.lessons.java.project.java_spec_backoffice_spring.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "platforms")
public class Platform {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 30, message = "Name cannot be longer than 30 characters.")
  @NotBlank(message = "Title cannot be blank, empty or null.")
  private String name;

  @Column(name = "badge_color")
  private String badgeColor;

  // ! MANY TO MANY RELATIONS

  @ManyToMany(mappedBy = "platforms")
  @JsonBackReference
  private List<Anime> anime;

  // ! GETTERS AND SETTERS

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBadgeColor() {
    return this.badgeColor;
  }

  public void setBadgeColor(String badgeColor) {
    this.badgeColor = badgeColor;
  }

  public List<Anime> getAnime() {
    return this.anime;
  }

  public void setAnime(List<Anime> anime) {
    this.anime = anime;
  }

}
