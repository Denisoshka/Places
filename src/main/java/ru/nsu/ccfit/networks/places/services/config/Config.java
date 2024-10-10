package ru.nsu.ccfit.networks.places.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
  @Bean
  public WebClient graphhopperWebClient(WebClient.Builder builder) {
    return builder
        .baseUrl("https://graphhopper.com/api/1/geocode")
        .build();
  }
  
  @Bean
  public WebClient kudaGoWebClient(WebClient.Builder builder) {
      return builder
          .baseUrl("https://kudago.com/public-api/v1.4").build();
  }
  
  @Bean
  WebClient openweatherWebClient(WebClient.Builder builder) {
    return builder
        .baseUrl("https://api.openweathermap.org/data/2.5/weather")
        .build();
  }
  
  /*@Bean
  public WebClient openTripMapWebClient(WebClient.Builder builder) {
    return builder.baseUrl("https://api.opentripmap.com/0.1").build();
  }*/
}
