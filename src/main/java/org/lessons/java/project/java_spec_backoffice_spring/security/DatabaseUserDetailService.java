package org.lessons.java.project.java_spec_backoffice_spring.security;

import java.util.Optional;

import org.lessons.java.project.java_spec_backoffice_spring.model.User;
import org.lessons.java.project.java_spec_backoffice_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> userAttempt = userRepository.findByUsername(username);

    if (userAttempt.isEmpty()) {

      throw new UsernameNotFoundException("No user available with this username!");
    }

    return new DatabaseUserDetails(userAttempt.get());
  }
}
