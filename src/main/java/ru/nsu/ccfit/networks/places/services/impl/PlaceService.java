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
import ru.nsu.ccfit.networks.places.places.PlaceInfoDTO;
import ru.nsu.ccfit.networks.places.services.AbstractPlaceService;
import ru.nsu.ccfit.networks.places.services.mappers.PlacesDTOMapper;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.KudaGoResponse;

import java.util.List;

@Component
public class PlaceService implements AbstractPlaceService {
  private static final Logger LOG = LoggerFactory.getLogger(PlaceService.class);
  private final WebClient kudaGoWebClient;
  private final WebClient graphhopperWebClient;
  private final PlacesDTOMapper placesDTOMapper;
  private final String graphhopperApiKey;
  @Value("${graphhopper.api.places.limit}")
  private int placesLimit;

  @Autowired
  public PlaceService(
      WebClient kudaGoWebClient,
      WebClient graphhopperWebClient,
      PlacesDTOMapper placesDTOMapper,
      @Value("${graphhopper.api.key}") String graphhopperApiKey
  ) {
    this.kudaGoWebClient = kudaGoWebClient;
    this.graphhopperWebClient = graphhopperWebClient;
    this.placesDTOMapper = placesDTOMapper;
    this.graphhopperApiKey = graphhopperApiKey;
  }


  @Cacheable("ListPlacesByName")
  @Override
  public Mono<List<LocationDTO>> getPlacesByName(final String q) {
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
        .map(response -> placesDTOMapper.toListPlaceDTO(response.getHits())).log();
  }


  @Override
  public Mono<List<LocationInfoDTO>> getPlacesInRadius(
      final double lat,
      final double lon,
      final int radius
  ) {
    return kudaGoWebClient.get().uri(
            uriBuilder -> uriBuilder
                .path("/places/")
                .queryParam("radius", radius)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .build()
        ).retrieve()
        .onStatus(HttpStatusCode::isError, ClientResponse::createException)
        .bodyToMono(KudaGoResponse.Radius.class)
        .map(val -> placesDTOMapper.toNearByPlaceInfoDTO(val.getResults()));
  }

  @Override
  public Mono<PlaceInfoDTO> getPlaceInfo(final String xid) {
    return kudaGoWebClient.get().uri(
            uriBuilder -> uriBuilder.path("/places/" + xid).build()
        ).retrieve()
        .onStatus(HttpStatusCode::isError, ClientResponse::createException)
        .bodyToMono(KudaGoResponse.Details.class)
        .map(placesDTOMapper::toPlaceInfoDTO);
  }
}
