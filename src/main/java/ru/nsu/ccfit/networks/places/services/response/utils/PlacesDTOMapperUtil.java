package ru.nsu.ccfit.networks.places.services.response.utils;

import org.mapstruct.Named;

@Named("PlacesDTOMapperUtil")
public class PlacesDTOMapperUtil {
  @Named("buildAddress")
  public static String getAddress(final String country,
                                  final String city, final String state,
                                  final String street, final String housenumber,
                                  final String postcode) {
    var addressBuilder = new StringBuilder();
    if (country != null) {
      addressBuilder.append(country).append(", ");
    }
    if (city != null) {
      addressBuilder.append(city).append(", ");
    }
    if (state != null) {
      addressBuilder.append(state).append(", ");
    }
    if (street != null) {
      addressBuilder.append(street).append(", ");
    }
    if (housenumber != null) {
      addressBuilder.append(housenumber).append(", ");
    }
    if (postcode != null) {
      addressBuilder.append(postcode);
    }
    return addressBuilder.toString();
  }
}
