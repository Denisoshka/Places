package ru.nsu.ccfit.networks.places.services.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GraphhopperGeocodingResponse {
  
  private List<hittedLocation> hits;
//  private Long took;
  
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class hittedLocation {
    
    private Point point;
    //    private String osmId;
//    private String osmType;
//    private String osmKey;
    private String name;
    private String country;
    private String city;
    private String state;
    private String street;
    private String housenumber;
    private String postcode;
    
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Point {
      private Double lat;
      private Double lng;
    }
  }
}

