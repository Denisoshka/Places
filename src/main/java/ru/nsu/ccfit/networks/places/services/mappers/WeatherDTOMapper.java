package ru.nsu.ccfit.networks.places.services.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.nsu.ccfit.networks.places.places.WeatherDTO;
import ru.nsu.ccfit.networks.places.services.response.OpenWeatherResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WeatherDTOMapper {
  @Mapping(target = "lon", source = "coord.lon")
  @Mapping(target = "lat", source = "coord.lat")
  @Mapping(target = "cloudiness", source = "clouds.all")
  @Mapping(target = "rainHeight", source = "rain.oneHour")
  @Mapping(target = "snowHeight", source = "snow.oneHour")
  WeatherDTO toWeatherDTO(OpenWeatherResponse response);
}
