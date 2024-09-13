package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.LocationDTO;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.mappers.utils.PlacesDTOMapperUtil;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {
        PlacesDTOMapperUtil.class
    }
)
public interface PlacesDTOMapper {
  @Mapping(target = "lat", source = "point.lat")
  @Mapping(target = "lng", source = "point.lng")
  @Mapping(
      target = "address",
      qualifiedByName = {
          "PlacesDTOMapperUtil",
          "buildAddress"
      },
      source = "."
  )
  LocationDTO toLocation(GraphhopperGeocodingResponse.hittedLocation graphhopperGeocodingEndpoint);
  
  List<LocationDTO> toListPlacesDTOMapper(List<GraphhopperGeocodingResponse.hittedLocation> hitLocations);
}
