package ru.nsu.ccfit.networks.places.services.mappers.utils;

import org.mapstruct.Named;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;

@Named("MappersUtil")
public class MappersUtil {
  @Named("buildAddress")
  public static String buildAddress(GraphhopperGeocodingResponse.HittedLocation source) {
    final String country = source.getCountry();
    final String city = source.getCity();
    final String state = source.getState();
    final String street = source.getStreet();
    final String housenumber = source.getHousenumber();
    final String postcode = source.getPostcode();
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
