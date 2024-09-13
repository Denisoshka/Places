package ru.nsu.ccfit.networks.places.controllers.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.LocationDTO;
import ru.nsu.ccfit.networks.places.places.LocationInfoDTO;
import ru.nsu.ccfit.networks.places.services.AbstractPlaceService;

import java.util.List;

//@RequestMapping
@RestController
@RequestMapping("/api/v1/places")
public final class PlaceController {
  private final AbstractPlaceService placeService;
  
  @Autowired
  public PlaceController(AbstractPlaceService placeService) {
    this.placeService = placeService;
  }
  
  @GetMapping("searchByPlace")
  public Mono<List<LocationDTO>> getPlaces(
      @RequestParam(name = "place") final String q
  ) {
    return placeService.ListPlacesByName(q);
  }
  
  Mono<List<LocationInfoDTO>> getPopulatedPlaces(
      final double latitude, final double longitude
  ){
    return placeService.ListPlacesByCords(latitude, longitude, 100)
  }
}
