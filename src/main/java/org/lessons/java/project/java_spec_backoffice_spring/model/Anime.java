package org.lessons.java.project.java_spec_backoffice_spring.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "anime")
public class Anime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 50, message = "Title cannot be longer than 50 characters.")
  @NotBlank(message = "Title cannot be blank, empty or null.")
  private String title;

  @Lob
  @NotBlank(message = "Description cannot be blank, empty or null.")
  private String description;

  @Column(name = "image_url")
  @NotBlank(message = "Image url cannot be blank, empty or null.")
  private String imageUrl;

  @Min(1)
  @Column(name = "episodes")
  @NotNull(message = "Number of episodes cannot be null.")
  private Integer numberOfEpisodes;

  @Column(name = "release_date")
  @NotNull(message = "Release date must be selected.")
  @PastOrPresent(message = "Date must be in the past or present.")
  private LocalDate releaseDate;

  // ! MANY TO MANY RELATIONS
  @ManyToMany
  @JoinTable(name = "anime_platform", joinColumns = @JoinColumn(name = "anime_id"), inverseJoinColumns = @JoinColumn(name = "platform_id"))
  private List<Platform> platforms;

  // ! GETTERS AND SETTERS
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Integer getNumberOfEpisodes() {
    return this.numberOfEpisodes;
  }

  public void setNumberOfEpisodes(Integer numberOfEpisodes) {
    this.numberOfEpisodes = numberOfEpisodes;
  }

  public LocalDate getReleaseDate() {
    return this.releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public List<Platform> getPlatforms() {
    return this.platforms;
  }

  public void setPlatforms(List<Platform> platforms) {
    this.platforms = platforms;
  }

}
