package org.lessons.java.project.java_spec_backoffice_spring.controller;

import org.lessons.java.project.java_spec_backoffice_spring.model.Anime;
import org.lessons.java.project.java_spec_backoffice_spring.model.Platform;
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
@RequestMapping("/platforms")
public class PlatformController {

  @Autowired
  private PlatformService platformService;

  @GetMapping
  public String index(Model model) {

    model.addAttribute("platforms", platformService.findAll());
    return "platforms/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    model.addAttribute("platform", platformService.getById(id));
    return "platforms/show";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("platform", new Platform());
    return "platforms/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("platform") Platform platformForm, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "platforms/create-or-edit";
    }

    platformService.create(platformForm);
    return "redirect:/platforms";
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable Integer id, Model model) {

    model.addAttribute("platform", platformService.getById(id));
    model.addAttribute("edit", true);

    return "platforms/create-or-edit";
  }

  @PostMapping("/{id}/edit")
  public String update(@Valid @ModelAttribute("platform") Platform platformForm, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "platforms/create-or-edit";
    }

    platformService.update(platformForm);
    return "redirect:/platforms/" + platformForm.getId();
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, Model model) {

    Platform platformToDelete = platformService.getById(id);

    for (Anime anime : platformToDelete.getAnime()) {
      anime.getPlatforms().remove(platformToDelete);
    }

    platformService.deleteById(id);
    return "redirect:/platforms";
  }
}
