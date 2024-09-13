package ru.nsu.ccfit.networks.places.controllers.api.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/")
public class MainController {
  @GetMapping()
  public Mono<String> get() {
    return Mono.just("index.html");
  }
}
