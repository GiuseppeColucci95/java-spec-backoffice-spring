package org.lessons.java.project.java_spec_backoffice_spring.repository;

import java.util.Optional;
import org.lessons.java.project.java_spec_backoffice_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);
}
