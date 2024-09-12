package ru.nsu.ccfit.networks.places.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import ru.nsu.ccfit.networks.places.services.AbstractPlaceService;
import ru.nsu.ccfit.networks.places.places.Location;
import ru.nsu.ccfit.networks.places.services.mappers.PlacesDTOMapper;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingEndpoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class PlaceService implements AbstractPlaceService {
  @Value("${graphhopper.api.places.limit}")
  private static int PLACES_LIMIT;
  private static final int RETRY_Q = 3;
  private static final int RETRY_DELAY = 5;
  
  private final WebClient graphhopperWebClient;
  private final PlacesDTOMapper mapper;
  
  @Autowired
  public PlaceService(WebClient graphhopperWebClient, PlacesDTOMapper mapper) {
    this.graphhopperWebClient = graphhopperWebClient;
    this.mapper = mapper;
  }
  
  @Cacheable("ListPlacesByName")
  @Override
  public Mono<List<Location>> ListPlacesByName(String q) {
    return graphhopperWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("q", q)
            .queryParam("limit", PLACES_LIMIT)
            .build())
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            ClientResponse::createException
        )
        .bodyToMono(GraphhopperGeocodingEndpoint.class)
        .retryWhen(
            Retry.fixedDelay(RETRY_Q, Duration.ofSeconds(RETRY_DELAY))
        )
        .map(response -> mapper.toListPlacesDTOMapper(response.getHits()));
  }
  
  @Override
  public Mono<List<Location>> ListPlacesByCords(double latitude, double longitude) {
    /*graphhopperWebClient.get().uri(
        uriBuilder -> uriBuilder
            .queryParam("point", latitude + "," + longitude)
            .queryParam("reverse", "true")
            .queryParam("limit", PLACES_LIMIT)
            .build()
    ).retrieve().onStatus()*/
    return Mono.just(new ArrayList<>());
  }
  
}
