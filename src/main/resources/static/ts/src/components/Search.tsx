import React, {useState} from 'react';
import axios from 'axios';
import {LocationDTO, LocationInfoDTO, PlaceInfoDTO} from "../dto/dto"
import {PlacesApiV1Url} from "../Consts";

const SearchRadius = 1000;

const Search: React.FC = () => {
  const [query, setQuery] = useState<string>('');
  const [results, setResults] = useState<LocationDTO[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>('');
  const [selectedLocation, setSelectedLocation] = useState<LocationInfoDTO | null>(null);
  const [selectedPlace, setSelectedPlace] = useState<PlaceInfoDTO | null>(null);

  // Поиск локаций по запросу
  const handleSearch = async (event: React.FormEvent) => {
    event.preventDefault();
    setLoading(true);
    setError('');
    setSelectedLocation(null);
    setSelectedPlace(null);

    try {
      const response = await axios.get<LocationDTO[]>(
          `${PlacesApiV1Url}/search_location?place=${query}`
      );
      setResults(response.data);
    } catch (error) {
      setError('Ошибка при запросе данных');
    } finally {
      setLoading(false);
    }
  };

  // Запрос для получения информации о локации
  const handleLocationClick = async (lat: number, lan: number) => {
    setLoading(true);
    setError('');

    try {
      const response = await axios.get<LocationInfoDTO>(`${PlacesApiV1Url}/populated_places?lat=${lat}&lan=${lan}&r=${SearchRadius}`);
      setSelectedLocation(response.data);
      setSelectedPlace(null); // Сбрасываем выбранный объект PlaceInfoDTO
    } catch (error) {
      setError('Ошибка при получении информации о локации');
    } finally {
      setLoading(false);
    }
  };

  // Запрос для получения информации о месте (PlaceInfoDTO)
  const handlePlaceClick = async (placeId: string) => {
    setLoading(true);
    setError('');

    try {
      const response = await axios.get<PlaceInfoDTO>(`https://api.example.com/places/${placeId}`);
      setSelectedPlace(response.data); // Устанавливаем выбранный объект PlaceInfoDTO
    } catch (error) {
      setError('Ошибка при запросе информации о месте');
    } finally {
      setLoading(false);
    }
  };

  // Функция для закрытия деталей локации
  const closeLocationDetails = () => {
    setSelectedLocation(null);
  };

  const renderLocationDetails = () => {
    if (!selectedLocation) return null;
    return (
        <div>
          <button onClick={closeLocationDetails}>Закрыть</button>
          <h2>{selectedLocation.name}</h2>
          <p>{selectedLocation.description}</p>
          <p>Адрес: {selectedLocation.address}</p>
          <p>Координаты: {selectedLocation.lat}, {selectedLocation.lon}</p>
          {selectedLocation.images.length > 0 && (
              <div>
                <h4>Изображения:</h4>
                {selectedLocation.images.map((image, index) => (
                    <img key={index} src={image}
                         alt={`Изображение ${index + 1}`}
                         width="200"/>
                ))}
              </div>
          )}
          {selectedLocation.tags.length > 0 && (
              <div>
                <h4>Теги:</h4>
                <ul>
                  {selectedLocation.tags.map((tag, index) => (
                      <li key={index}>{tag}</li>
                  ))}
                </ul>
              </div>
          )}
          {selectedLocation.isClosed &&
              <p style={{color: 'red'}}>Место закрыто</p>}

          {/* Кнопка для получения PlaceInfoDTO */}
          <button
              onClick={() => handlePlaceClick(selectedLocation.id)}>
            Получить информацию о месте
          </button>
        </div>
    )
  }

  const renderLocationList = () => {
    return (
        // Отображаем список локаций, если нет выбранной локации
        <ul>
          {results.map((location, index) => (
              <li key={index}
                  onClick={() => handleLocationClick(location.lat, location.lng)}>
                <h3>{location.name}</h3>
                <p>Адрес: {location.address}</p>
                <p>Координаты: {location.lat}, {location.lng}</p>
              </li>
          ))}
        </ul>
    )
  }

  const renderSelectedPlaceDetails = (selectedPlace: PlaceInfoDTO) => {
    return (
        <div style={{flex: 1, marginLeft: '20px'}}>
          <h2>Информация о месте</h2>
          <h3>{selectedPlace.name}</h3>
          <p>{selectedPlace.description}</p>
          <p>Адрес: {selectedPlace.address}</p>
          {selectedPlace.tags.length > 0 && (
              <div>
                <h4>Теги:</h4>
                <ul>
                  {selectedPlace.tags.map((tag, index) => (
                      <li key={index}>{tag}</li>
                  ))}
                </ul>
              </div>
          )}
          {selectedPlace.images.length > 0 && (
              <div>
                <h4>Изображения:</h4>
                {selectedPlace.images.map((image, index) => (
                    <img key={index} src={image}
                         alt={`Изображение ${index + 1}`}
                         width="200"/>
                ))}
              </div>
          )}
        </div>
    )
  }

  return (
      <div style={{display: 'flex'}}>
        <div style={{flex: 1}}>
          <form onSubmit={handleSearch}>
            <input
                type="text"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Введите запрос"
            />
            <button type="submit">Поиск</button>
          </form>

          {loading && <p>Загрузка...</p>}
          {error && <p style={{color: 'red'}}>{error}</p>}
          {/* Если выбрана локация, показываем ее детали */}
          {selectedLocation ? renderLocationDetails() : renderLocationList()}
        </div>

        {/* Сбоку отображаем информацию о PlaceInfoDTO */}
        {selectedPlace && renderSelectedPlaceDetails(selectedPlace)}
      </div>
  );
};

export default Search;
