package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.NearByPlaceInfoDTO;
import ru.nsu.ccfit.networks.places.places.PlaceDTO;
import ru.nsu.ccfit.networks.places.places.PlaceInfoDTO;
import ru.nsu.ccfit.networks.places.services.mappers.utils.MappersUtil;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.OpenTripMapResponse;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {
        MappersUtil.class,
    }
)
public interface PlacesDTOMapper {
  @Mapping(target = "lat", source = "point.lat")
  @Mapping(target = "lng", source = "point.lng")
  @Mapping(
      target = "address",
      qualifiedByName = {
          "MappersUtil",
          "buildAddress"
      },
      source = "."
  )
  PlaceDTO toPlaceDTO(
      final GraphhopperGeocodingResponse.HittedLocation graphhopperGeocodingEndpoint
  );
  
  List<PlaceDTO> toListPlaceDTO(
      final List<GraphhopperGeocodingResponse.HittedLocation> hitLocations
  );
  
  @Mapping(target = "lat", source = "point.lat")
  @Mapping(target = "lon", source = "point.lon")
  @Mapping(
      target = "kinds",
      source = "kind"
  )
  NearByPlaceInfoDTO toNearByPlaceInfoDTO(
      final OpenTripMapResponse.Radius response
  );
  
  List<NearByPlaceInfoDTO> toNearByPlaceInfoDTO(
      final OpenTripMapResponse.Radius[] response
  );
  
  @Mapping(target = "descr", source = "info.descr")
  PlaceInfoDTO toPlaceInfoDTO(final OpenTripMapResponse.PlaceInfo placeInfo);
}
