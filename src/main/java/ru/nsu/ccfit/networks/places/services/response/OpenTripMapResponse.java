package ru.nsu.ccfit.networks.places.services.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class OpenTripMapResponse {
  private String name;
  private String[] kinds;
  private String wikidata;
  private int dist;
  private Point point;
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Point {
    private double lat;
    private double lon;
  }
}
