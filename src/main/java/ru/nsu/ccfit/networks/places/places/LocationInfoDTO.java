package ru.nsu.ccfit.networks.places.places;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationInfoDTO {
  private String name;
  private String[] kinds;
  private String wikidata;
  private int dist;
  private double lat;
  private double lon;
}
