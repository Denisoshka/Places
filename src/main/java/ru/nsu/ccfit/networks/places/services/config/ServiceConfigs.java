package ru.nsu.ccfit.networks.places.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class ServiceConfigs {
  @Value("${graphhopper.api.key}")
  private String apiKey;
  
  @Bean
  public WebClient graphhopperWebClient(WebClient.Builder builder) {
    return builder.baseUrl("https://graphhopper.com/api/1/geocode")
        .defaultUriVariables(Collections.singletonMap("key", apiKey)).build();
  }
}
