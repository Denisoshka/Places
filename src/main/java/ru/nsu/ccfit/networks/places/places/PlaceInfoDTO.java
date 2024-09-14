package ru.nsu.ccfit.networks.places.places;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceInfoDTO {
  private String name;
  private String[] kinds;
  private String wikipedia;
  private String image;
  private String descr;
}
