package ru.nsu.ccfit.networks.places.services;

import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.LocationInfoDTO;
import ru.nsu.ccfit.networks.places.places.LocationDTO;
import ru.nsu.ccfit.networks.places.places.PlaceInfoDTO;

import java.util.List;

public interface AbstractPlaceService {
  /**
   * @param q place name
   * @return {@code Mono<List<LocationDTO>>} places by name match
   */
  Mono<List<LocationDTO>> getPlacesByName(final String q);
  
  /**
   * @param lat    latitude
   * @param lon    longitude
   * @param radius in meters
   * @return {@code Mono<List<LocationInfoDTO>>} places nearby location
   */
  Mono<List<LocationInfoDTO>> getPlacesInRadius(
      final double lat,
      final double lon,
      final int radius
  );
  
  /**
   * @param xid unique identifier of the object in OpenTripMap
   * @return {@code Mono<PlaceInfoDTO>} place by xid info
   */
  Mono<PlaceInfoDTO> getPlaceInfo(final String xid);
}
