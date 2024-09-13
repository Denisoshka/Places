package ru.nsu.ccfit.networks.places.services;

import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.WeatherDTO;

public interface AbstractWeatherService {
  Mono<WeatherDTO> getWeather(double lat, double lng);
}
