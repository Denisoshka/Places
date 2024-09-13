package ru.nsu.ccfit.networks.places.places;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class WeatherDTO {
  private double lon;//
  private double lat;//
  private Weather[] weather;//
  private Main main;
  private int visibility;
  private Wind wind;
  private int rainHeight;
  private int snowHeight;
  private int cloudiness;
  private int timezone;
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Weather {
    private String main;
    private String description;
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
  public static class Rain {
    private double height;
  }
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Snow {
    private double height;
  }
}
