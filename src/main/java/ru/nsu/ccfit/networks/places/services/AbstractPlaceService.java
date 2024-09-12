package ru.nsu.ccfit.networks.places.services;

import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.Location;

import java.util.List;

public interface AbstractPlaceService {
  Mono<List<Location>> ListPlacesByName(String q);
  
  Mono<List<Location>> ListPlacesByCords(double latitude, double longitude);
}
