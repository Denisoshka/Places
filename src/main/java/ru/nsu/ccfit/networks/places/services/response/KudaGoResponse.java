package ru.nsu.ccfit.networks.places.services.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class KudaGoResponse {
  @Setter
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Radius {
    private int count;
    private Place[] results;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Place {
      private int id;
      private String title;
      private String shortTitle;
      private String address;
      private String description;
      private Image[] images;
//      @JsonProperty("is_closed")
      private boolean isClosed;
      private String[] tags;
      private Coords coords;

      @Setter
      @Getter
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Coords {
        private double lat;
        private double lon;
      }

      @Setter
      @Getter
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Image {
        private String image;
//      public Source source;
      }

      @Setter
      @Getter
      @NoArgsConstructor
      @AllArgsConstructor
      public static class Source {
        private String name;
        private String link;
      }
    }
  }

  @Setter
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Details {
    private int id;
    private String title;
    private String shortTitle; // изменено с short_title на shortTitle
    private String address;
    private String timetable;
    private String phone;
    private String bodyText;
    private String description;
    private String siteUrl;
    //    private Coords coords;
    private Image[] images;
    private boolean isClosed;
    private String[] categories;
    private String[] tags;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coords {
      private double lat;
      private double lon;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
      private String image;
//      private Source source;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Source {
      private String name;
      private String link;
    }
  }
}
