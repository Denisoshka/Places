package ru.nsu.ccfit.networks.places.controllers.api.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.nsu.ccfit.networks.places.places.WeatherDTO;
import ru.nsu.ccfit.networks.places.services.AbstractWeatherService;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {
  private final AbstractWeatherService weatherService;
  
  @Autowired
  public WeatherController(AbstractWeatherService weatherService) {
    this.weatherService = weatherService;
  }
  
  @GetMapping("getWeatherInPoint")
  public Mono<WeatherDTO> getWeather(
      @RequestParam(name = "lat") final double lat,
      @RequestParam(name = "lon") final double lng
  ) {
    return weatherService.getWeather(lat, lng);
  }
}
