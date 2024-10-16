import React from 'react';

// Импортируем интерфейсы
import {WeatherDTO} from '../dto/dto.ts';

interface WeatherComponentProps {
  weatherData: WeatherDTO;
}

const WeatherComponent: React.FC<WeatherComponentProps> = ({weatherData}) => {
  return (
    <div className="weather-container">
      <h2>Weather Information</h2>

      <div className="coordinates">
        <p><strong>Coordinates:</strong> {weatherData.lat}, {weatherData.lon}
        </p>
      </div>

      <div className="weather-info">
        <h3>Current Weather</h3>
        {weatherData.weather.map((w, index) => (
          <div key={index}>
            <p><strong>Main:</strong> {w.main}</p>
            <p><strong>Description:</strong> {w.description}</p>
          </div>
        ))}
      </div>

      <div className="main-info">
        <h3>Temperature</h3>
        <p><strong>Current Temp:</strong> {weatherData.main.temp}°C</p>
        <p><strong>Feels Like:</strong> {weatherData.main.feelsLike}°C</p>
        <p><strong>Min Temp:</strong> {weatherData.main.tempMin}°C</p>
        <p><strong>Max Temp:</strong> {weatherData.main.tempMax}°C</p>
        <p><strong>Pressure:</strong> {weatherData.main.pressure} hPa</p>
        <p><strong>Humidity:</strong> {weatherData.main.humidity}%</p>
        {weatherData.main.seaLevel &&
            <p><strong>Sea Level:</strong> {weatherData.main.seaLevel} hPa</p>}
        {weatherData.main.grndLevel &&
            <p><strong>Ground Level:</strong> {weatherData.main.grndLevel} hPa
            </p>}
      </div>

      <div className="wind-info">
        <h3>Wind</h3>
        <p><strong>Speed:</strong> {weatherData.wind.speed} m/s</p>
        <p><strong>Direction:</strong> {weatherData.wind.deg}°</p>
        {weatherData.wind.gust &&
            <p><strong>Gust:</strong> {weatherData.wind.gust} m/s</p>}
      </div>

      {weatherData.rainHeight && (
        <div className="rain-info">
          <h3>Rain</h3>
          <p><strong>Rain Height:</strong> {weatherData.rainHeight} mm</p>
        </div>
      )}

      {weatherData.snowHeight && (
        <div className="snow-info">
          <h3>Snow</h3>
          <p><strong>Snow Height:</strong> {weatherData.snowHeight} mm</p>
        </div>
      )}

      <div className="cloudiness-info">
        <p><strong>Cloudiness:</strong> {weatherData.cloudiness}%</p>
      </div>

      <div className="timezone-info">
        <p><strong>Timezone (offset):</strong> {weatherData.timezone} seconds
        </p>
      </div>
    </div>
  );
};

export default WeatherComponent;
