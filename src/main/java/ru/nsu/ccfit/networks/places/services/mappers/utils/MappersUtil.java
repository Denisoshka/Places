package ru.nsu.ccfit.networks.places.services.mappers.utils;

import org.mapstruct.Named;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.KudaGoResponse;

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
    if (addressBuilder.charAt(addressBuilder.length() - 1) == ' ' &&
        addressBuilder.charAt(addressBuilder.length() - 2) == ',') {
      addressBuilder.delete(addressBuilder.length() - 2, addressBuilder.length());
    }
    return addressBuilder.toString();
  }

  @Named("getRadiusPlaceImages")
  public String[] getRadiusPlaceImages(KudaGoResponse.Radius.Place.Image[] images) {
    var ret = new String[images.length];
    for (int i = 0; i < images.length; i++) {
      ret[i] = images[i].getImage();
    }
    return ret;
  }

  @Named("getDetailsImages")
  public String[] getDetailsImages(KudaGoResponse.Details.Image[] images) {
    var ret = new String[images.length];
    for (int i = 0; i < images.length; i++) {
      ret[i] = images[i].getImage();
    }
    return ret;
  }

  @Named("getPhones")
  public String[] getPhones(String phone) {
    return phone.split(",\\s*");
  }
}
