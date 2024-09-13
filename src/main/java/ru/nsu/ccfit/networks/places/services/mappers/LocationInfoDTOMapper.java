package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.LocationInfoDTO;
import ru.nsu.ccfit.networks.places.services.mappers.utils.LocationInfoDTOMapperUtil;
import ru.nsu.ccfit.networks.places.services.response.OpenTripMapResponse;

import java.util.List;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {
        LocationInfoDTOMapperUtil.class
    }
)
public interface LocationInfoDTOMapper {
  @Mapping(target = "lat", source = "point.lat")
  @Mapping(target = "lon", source = "point.lon")
  @Mapping(
      target = "kinds",
      qualifiedByName = {
          "LocationInfoDTOMapperUtil",
          "getKinds"
      }, source = "kind"
  )
  LocationInfoDTO toLocationInfoDTO(final OpenTripMapResponse openTripMapResponse);
  
  List<LocationInfoDTO> toLocationInfoDTOList(final OpenTripMapResponse[] openTripMapResponseList);
}
