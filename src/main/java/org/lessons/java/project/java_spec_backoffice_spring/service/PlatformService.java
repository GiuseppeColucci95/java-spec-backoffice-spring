package org.lessons.java.project.java_spec_backoffice_spring.service;

import java.util.List;

import org.lessons.java.project.java_spec_backoffice_spring.model.Platform;
import org.lessons.java.project.java_spec_backoffice_spring.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {

  @Autowired
  private PlatformRepository platformRepository;

  public List<Platform> findAll() {
    return platformRepository.findAll();
  }

  public Platform getById(Integer id) {
    return platformRepository.findById(id).get();
  }

  public Platform create(Platform platform) {
    return platformRepository.save(platform);
  }

  public Platform update(Platform platform) {
    return platformRepository.save(platform);
  }

  public void deleteById(Integer id) {
    Platform platformToDelete = getById(id);

    platformRepository.delete(platformToDelete);
  }

}
