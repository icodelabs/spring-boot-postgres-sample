package org.jug.algeria.controller;

import org.jug.algeria.domain.AppUser;
import org.jug.algeria.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

  final
  UserRepository userRepository;

  @Inject
  public HomeController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(value = "/hello")
  public String sayHello() {
    return "Hello there !";
  }

  @PostMapping(value = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public AppUser create(@PathVariable String username) {
    return userRepository.save(new AppUser(username));
  }

  @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AppUser> findAll() {
    final List<AppUser> resultList = new ArrayList<>();
    final Iterable<AppUser> all = userRepository.findAll();
    all.forEach(appUser -> resultList.add(appUser));
    return resultList;
  }

}
