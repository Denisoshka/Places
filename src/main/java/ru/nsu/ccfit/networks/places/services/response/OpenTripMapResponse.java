package ru.nsu.ccfit.networks.places.services.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public final class OpenTripMapResponse {
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Radius {
    private String name;
    private String xid;
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
  
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class PlaceInfo {
        private String kinds;
//    private Sources sources;
//    private Bbox bbox;
//    private Point point;
//    private String osm;
//    private String otm;
//    private String xid;
    private String name;
    private String wikipedia;
    private String image;
    //    private String wikidata;
//    private String rate;
    private Info info;
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Sources {
      private String geometry;
      private String[] attributes;
    }
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Bbox {
      private double lat_max;
      private double lat_min;
      private double lon_max;
      private double lon_min;
    }
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Point {
      private double lon;
      private double lat;
    }
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Info {
      private String descr;
      //      private String image;
//      private int img_width;
      //      private String src;
//      private int src_id;
//      private int img_height;
    }
  }
}
