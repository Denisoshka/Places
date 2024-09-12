package ru.nsu.ccfit.networks.places.controllers.apiv1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.Location;
import ru.nsu.ccfit.networks.places.services.AbstractPlaceService;

import java.util.List;

@RequestMapping
@RestController
public class PlaceController {
  private final AbstractPlaceService placeService;
  
  @Autowired
  public PlaceController(AbstractPlaceService placeService) {
    this.placeService = placeService;
  }
  
  @GetMapping("placesByName")
  public Mono<List<Location>> getPlaces(@RequestParam(name = "place_name") final String q) {
    return placeService.ListPlacesByName(q);
  }
  
}
