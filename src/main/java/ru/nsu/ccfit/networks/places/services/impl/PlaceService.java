package ru.nsu.ccfit.networks.places.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.LocationDTO;
import ru.nsu.ccfit.networks.places.places.LocationInfoDTO;
import ru.nsu.ccfit.networks.places.services.AbstractPlaceService;
import ru.nsu.ccfit.networks.places.services.mappers.LocationInfoDTOMapper;
import ru.nsu.ccfit.networks.places.services.mappers.PlacesDTOMapper;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.OpenTripMapResponse;

import java.util.List;

@Component
public class PlaceService implements AbstractPlaceService {
  private static final Logger LOG = LoggerFactory.getLogger(PlaceService.class);
  private final WebClient graphhopperWebClient;
  private final WebClient openTripMapWebClient;
  private final PlacesDTOMapper mapper;
  private final String graphhopperApiKey;
  private final String opentripmapApiKey;
  private final LocationInfoDTOMapper locationInfoDTOMapper;
  @Value("${graphhopper.api.places.limit}")
  private int placesLimit;
//  @Value("${graphhopper.api.key}")
  
  @Autowired
  public PlaceService(
      WebClient graphhopperWebClient,
      WebClient openTripMapWebClient,
      PlacesDTOMapper placesDTOMapper,
      LocationInfoDTOMapper locationInfoDTOMapper,
      @Value("${graphhopper.api.key}") String graphhopperApiKey,
      @Value("${opentripmap.api.key}") String opentripmapApiKey
  ) {
    this.mapper = placesDTOMapper;
    this.graphhopperApiKey = graphhopperApiKey;
    this.opentripmapApiKey = opentripmapApiKey;
    this.graphhopperWebClient = graphhopperWebClient;
    this.openTripMapWebClient = openTripMapWebClient;
    this.locationInfoDTOMapper = locationInfoDTOMapper;
  }
  
  /**
   * @param q place name
   * @return {@code Mono<List<LocationDTO>>} places by name match
   */
  @Cacheable("ListPlacesByName")
  @Override
  public Mono<List<LocationDTO>> ListPlacesByName(final String q) {
    return graphhopperWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("key", graphhopperApiKey)
            .queryParam("q", q)
            .queryParam("limit", placesLimit)
            .build())
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            ClientResponse::createException
        )
        .bodyToMono(GraphhopperGeocodingResponse.class)
        .map(response -> mapper.toListPlacesDTOMapper(response.getHits())).log();
  }
  
  /**
   * @param lat latitude
   * @param lon longitude
   * @param radius in meters
   * @return {@code Mono<List<LocationInfoDTO>>} places nearby location
   */
  @Override
  public Mono<List<LocationInfoDTO>> ListPlacesByCords(
      final double lat,
      final double lon,
      final int radius
  ) {
    return openTripMapWebClient.get().uri(
            uriBuilder -> uriBuilder
                .queryParam("apikey", opentripmapApiKey)
                .queryParam("lang", "en")
                .queryParam("radius", radius)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .build()
        ).retrieve()
        .onStatus(
            HttpStatusCode::isError,
            ClientResponse::createException
        ).bodyToMono(OpenTripMapResponse[].class)
        .map(locationInfoDTOMapper::toLocationInfoDTOList);
  }
  
}
