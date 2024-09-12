package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.Location;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingEndpoint;
import ru.nsu.ccfit.networks.places.services.response.utils.PlacesDTOMapperUtil;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {
        PlacesDTOMapperUtil.class
    })
public interface PlacesDTOMapper {
  @Mapping(target = "name", source = "name")
  @Mapping(target = "lat", source = "point.lat")
  @Mapping(target = "lng", source = "point.lng")
  @Mapping(
      target = "address",
      qualifiedByName = {
          "PlacesDTOMapperUtil",
          "buildAddress"
      },
      source = "country",
      dependsOn = {
          "city",
          "state",
          "street",
          "housenumber",
          "postcode"
      }
  )
  Location toLocation(GraphhopperGeocodingEndpoint graphhopperGeocodingEndpoint);
  
  List<Location> toListPlacesDTOMapper(List<GraphhopperGeocodingEndpoint.hittedLocation> hitLocations);
}
