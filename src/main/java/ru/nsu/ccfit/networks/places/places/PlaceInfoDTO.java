package ru.nsu.ccfit.networks.places.places;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceInfoDTO {
  private String name;
  private String shortName;
  private String shortDescription;
  private String description;
  private String address;
  private String url;
  private String[] phone;
  private String[] images;
  private String[] categories;
  private String[] tags;
  private boolean isClosed;
}
