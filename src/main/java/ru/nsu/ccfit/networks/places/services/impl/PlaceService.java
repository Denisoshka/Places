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
import ru.nsu.ccfit.networks.places.places.NearByPlaceInfoDTO;
import ru.nsu.ccfit.networks.places.places.PlaceDTO;
import ru.nsu.ccfit.networks.places.places.PlaceInfoDTO;
import ru.nsu.ccfit.networks.places.services.AbstractPlaceService;
import ru.nsu.ccfit.networks.places.services.mappers.PlacesDTOMapper;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.OpenTripMapResponse;

import java.util.List;

@Component
public class PlaceService implements AbstractPlaceService {
  private static final Logger LOG = LoggerFactory.getLogger(PlaceService.class);
  private final WebClient graphhopperWebClient;
  private final WebClient openTripMapWebClient;
  private final PlacesDTOMapper placesDTOMapper;
  private final String graphhopperApiKey;
  private final String opentripmapApiKey;
  @Value("${graphhopper.api.places.limit}")
  private int placesLimit;
//  @Value("${graphhopper.api.key}")
  
  @Autowired
  public PlaceService(
      WebClient graphhopperWebClient,
      WebClient openTripMapWebClient,
      PlacesDTOMapper placesDTOMapper,
      @Value("${graphhopper.api.key}") String graphhopperApiKey,
      @Value("${opentripmap.api.key}") String opentripmapApiKey
  ) {
    this.placesDTOMapper = placesDTOMapper;
    this.graphhopperApiKey = graphhopperApiKey;
    this.opentripmapApiKey = opentripmapApiKey;
    this.graphhopperWebClient = graphhopperWebClient;
    this.openTripMapWebClient = openTripMapWebClient;
  }
  
  
  @Cacheable("ListPlacesByName")
  @Override
  public Mono<List<PlaceDTO>> getPlacesByName(final String q) {
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
  public Mono<List<NearByPlaceInfoDTO>> getPlacesByCords(
      final double lat,
      final double lon,
      final int radius
  ) {
    return openTripMapWebClient.get().uri(
            uriBuilder -> uriBuilder
                .path("en/places/radius")
                .queryParam("apikey", opentripmapApiKey)
                .queryParam("lang", "en")
                .queryParam("radius", radius)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .build()
        ).retrieve()
        .onStatus(HttpStatusCode::isError, ClientResponse::createException)
        .bodyToMono(OpenTripMapResponse.Radius[].class)
        .map(placesDTOMapper::toNearByPlaceInfoDTO);
  }
  
  @Override
  public Mono<PlaceInfoDTO> getPlaceInfo(final String xid) {
    return openTripMapWebClient.get().uri(
            uriBuilder -> uriBuilder.path("en/places/xid/" + xid).build()
        ).retrieve()
        .onStatus(HttpStatusCode::isError, ClientResponse::createException)
        .bodyToMono(OpenTripMapResponse.PlaceInfo.class)
        .map(placesDTOMapper::toPlaceInfoDTO);
  }
    /*
    /{lang}/places/xid/{xid}
    * */
}
