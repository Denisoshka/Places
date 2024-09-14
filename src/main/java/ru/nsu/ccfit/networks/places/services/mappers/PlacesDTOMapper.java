package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.NearByPlaceInfoDTO;
import ru.nsu.ccfit.networks.places.places.PlaceDTO;
import ru.nsu.ccfit.networks.places.places.PlaceInfoDTO;
import ru.nsu.ccfit.networks.places.services.mappers.utils.LocationInfoDTOMapperUtil;
import ru.nsu.ccfit.networks.places.services.mappers.utils.PlacesDTOMapperUtil;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.OpenTripMapResponse;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {
        PlacesDTOMapperUtil.class,
        LocationInfoDTOMapperUtil.class
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
  PlaceDTO toPlaceDTO(GraphhopperGeocodingResponse.hittedLocation graphhopperGeocodingEndpoint);
  
  List<PlaceDTO> toListPlaceDTO(List<GraphhopperGeocodingResponse.hittedLocation> hitLocations);
  
  @Mapping(target = "lat", source = "point.lat")
  @Mapping(target = "lon", source = "point.lon")
  @Mapping(
      target = "kinds",
      qualifiedByName = {
          "LocationInfoDTOMapperUtil",
          "getKinds"
      },
      source = "kind"
  )
  NearByPlaceInfoDTO toNearByPlaceInfoDTO(final OpenTripMapResponse.Radius response);
  
  List<NearByPlaceInfoDTO> toNearByPlaceInfoDTO(
      final OpenTripMapResponse.Radius[] response
  );
  
  @Mapping(
      target = "kinds",
      qualifiedByName = {
          "LocationInfoDTOMapperUtil",
          "getKinds"
      },
      source = "kinds"
  )
  PlaceInfoDTO toPlaceInfoDTO(final OpenTripMapResponse.PlaceInfo placeInfo);
}
