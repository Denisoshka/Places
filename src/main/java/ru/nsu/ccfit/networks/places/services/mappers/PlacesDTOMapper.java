package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.LocationDTO;
import ru.nsu.ccfit.networks.places.places.LocationInfoDTO;
import ru.nsu.ccfit.networks.places.places.PlaceInfoDTO;
import ru.nsu.ccfit.networks.places.services.mappers.utils.MappersUtil;
import ru.nsu.ccfit.networks.places.services.response.GraphhopperGeocodingResponse;
import ru.nsu.ccfit.networks.places.services.response.KudaGoResponse;

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
  LocationDTO toPlaceDTO(
      final GraphhopperGeocodingResponse.HittedLocation graphhopperGeocodingEndpoint
  );

  List<LocationDTO> toListPlaceDTO(
      final List<GraphhopperGeocodingResponse.HittedLocation> hitLocations
  );

  @Mapping(target = "lat", source = "coords.lat")
  @Mapping(target = "lon", source = "coords.lon")
  @Mapping(target = "name", source = "title")
  @Mapping(target = "shortName", source = "shortTitle")
  @Mapping(target = "images", source = "images",
      qualifiedByName = {"MappersUtil", "getRadiusPlaceImages"})
  LocationInfoDTO toNearByPlaceInfoDTO(
      final KudaGoResponse.Radius.Place response
  );

  List<LocationInfoDTO> toNearByPlaceInfoDTO(
      final KudaGoResponse.Radius.Place[] response
  );

  @Mapping(target = "name", source = "title")
  @Mapping(target = "shortName", source = "shortTitle")
  @Mapping(target = "description", source = "bodyText")
  @Mapping(target = "shortDescription", source = "description")
  @Mapping(target = "url", source = "siteUrl")
  @Mapping(target = "images", source = "images",
      qualifiedByName = {"MappersUtil", "getDetailsImages"})
  @Mapping(target = "phone", source = "phone",
      qualifiedByName ={"MappersUtil", "getPhones"})
  PlaceInfoDTO toPlaceInfoDTO(final KudaGoResponse.Details details);
}
