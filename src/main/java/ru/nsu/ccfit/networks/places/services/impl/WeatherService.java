package ru.nsu.ccfit.networks.places.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.WeatherDTO;
import ru.nsu.ccfit.networks.places.services.AbstractWeatherService;
import ru.nsu.ccfit.networks.places.services.mappers.WeatherDTOMapper;
import ru.nsu.ccfit.networks.places.services.response.OpenWeatherResponse;


@Component
public class WeatherService implements AbstractWeatherService {
  private final WeatherDTOMapper mapper;
  private final WebClient openweatherWebClient;
  private final String apiKey;
  
  @Autowired
  public WeatherService(
      WeatherDTOMapper mapper,
      WebClient openweatherWebClient,
      @Value("${openweather.api.key}") String apiKey
  ) {
    this.openweatherWebClient = openweatherWebClient;
    this.mapper = mapper;
    this.apiKey = apiKey;
  }
  
  @Override
  public Mono<WeatherDTO> getWeather(double lat, double lng) {
    return openweatherWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("appid", apiKey)
            .queryParam("lat", lat)
            .queryParam("lon", lng)
            .queryParam("units", "metric")
            .build()
        )
        .retrieve()
        .onStatus(
            HttpStatusCode::isError,
            ClientResponse::createException
        ).bodyToMono(OpenWeatherResponse.class)
        .map(mapper::toWeatherDTO).log();
  }
}
