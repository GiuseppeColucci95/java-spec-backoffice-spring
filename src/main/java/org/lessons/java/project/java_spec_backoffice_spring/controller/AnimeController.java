package org.lessons.java.project.java_spec_backoffice_spring.controller;

import org.lessons.java.project.java_spec_backoffice_spring.model.Anime;
import org.lessons.java.project.java_spec_backoffice_spring.model.Platform;
import org.lessons.java.project.java_spec_backoffice_spring.service.AnimeService;
import org.lessons.java.project.java_spec_backoffice_spring.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/anime")
public class AnimeController {

  @Autowired
  private AnimeService animeService;

  @Autowired
  private PlatformService platformService;

  @GetMapping
  public String index(Model model) {

    model.addAttribute("anime", animeService.findAll());

    return "anime/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    model.addAttribute("anime", animeService.getById(id));

    return "anime/show";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("anime", new Anime());
    model.addAttribute("platforms", platformService.findAll());

    return "anime/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("anime") Anime animeForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("platforms", platformService.findAll());
      return "anime/create-or-edit";
    }

    animeService.create(animeForm);
    return "redirect:/anime";
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable Integer id, Model model) {

    model.addAttribute("anime", animeService.getById(id));
    model.addAttribute("platforms", platformService.findAll());
    model.addAttribute("edit", true);

    return "anime/create-or-edit";
  }

  @PostMapping("/{id}/edit")
  public String update(@Valid @ModelAttribute("anime") Anime animeForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("platforms", platformService.findAll());
      return "anime/create-or-edit";
    }

    animeService.update(animeForm);
    return "redirect:/anime/" + animeForm.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Anime animeToDelete = animeService.getById(id);

    for (Platform platformToDelete : animeToDelete.getPlatforms()) {
      platformToDelete.getAnime().remove(animeToDelete);
    }

    animeService.deleteById(id);
    return "redirect:/anime";
  }
}
