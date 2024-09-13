package ru.nsu.ccfit.networks.places.services.mappers.utils;

import org.mapstruct.Named;

@Named("LocationInfoDTOMapperUtil")
public class LocationInfoDTOMapperUtil {
  @Named("getKinds")
  public static String[] getKinds(String kind) {
    return kind.split(",");
  }
}
