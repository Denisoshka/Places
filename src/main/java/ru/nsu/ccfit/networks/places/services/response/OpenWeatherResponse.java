package ru.nsu.ccfit.networks.places.services.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherResponse {
  private Coord coord;
  private Weather[] weather;
  private String base;
  private Main main;
  private int visibility;
  private Wind wind;
  private Rain rain;
  private Snow snow;
  private Clouds clouds;
  //  private long dt;
//  private Sys sys;
  private int timezone;
//  private int id;
//  private String name;
//  private int cod;
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Coord {
    public double lon;
    public double lat;
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Main {
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;
    private int seaLevel;
    private int grndLevel;
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Wind {
    private double speed;
    private int deg;
    private double gust;
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Rain {
    @JsonProperty("1h")
    private double oneHour;
    /*@JsonProperty("3h")
    public double threeHours;*/
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Snow {
    @JsonProperty("1h")
    private double oneHour;
    /*@JsonProperty("3h")
    public double threeHours;*/
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Clouds {
    private int all;
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
  }
}

