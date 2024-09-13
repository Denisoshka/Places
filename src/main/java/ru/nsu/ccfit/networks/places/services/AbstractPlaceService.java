package ru.nsu.ccfit.networks.places.services;

import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.LocationDTO;
import ru.nsu.ccfit.networks.places.places.LocationInfoDTO;

import java.util.List;

public interface AbstractPlaceService {
  Mono<List<LocationDTO>> ListPlacesByName(final String q);
  
  Mono<List<LocationInfoDTO>> ListPlacesByCords(final double latitude,
                                                final double longitude,
                                                final int radius);
}
