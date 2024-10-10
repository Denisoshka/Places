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
  private String id;
  private String name;
  private String shortName;
  private String address;
  private String description;
  private String[] tags;
  private String[] images;
  private boolean isClosed;
  private double lat;
  private double lon;
}
