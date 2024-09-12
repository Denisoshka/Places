package ru.nsu.ccfit.networks.places.places;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Location {
  private Double lat;
  private Double lng;
  private String name;
  private String address;
}
