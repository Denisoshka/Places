import React, { useState } from 'react';
import axios from 'axios';

// Типы для описания данных
interface LocationDTO {
    lat: number;
    lng: number;
    name: string;
    address: string;
}

interface LocationInfoDTO {
    id: string;
    name: string;
    shortName: string;
    address: string;
    description: string;
    tags: string[];
    images: string[];
    isClosed: boolean;
    lat: number;
    lon: number;
}

const Search: React.FC = () => {
    const [query, setQuery] = useState<string>('');
    const [results, setResults] = useState<LocationDTO[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string>('');
    const [selectedLocation, setSelectedLocation] = useState<LocationInfoDTO | null>(null);

    // Функция для отправки поискового запроса
    const handleSearch = async (event: React.FormEvent) => {
        event.preventDefault();
        setLoading(true);
        setError('');
        setSelectedLocation(null); // Сбрасываем выбранную локацию, если есть

        try {
            const response = await axios.get<LocationDTO[]>(`https://api.example.com/search?q=${query}`);
            setResults(response.data);
        } catch (error) {
            setError('Ошибка при запросе данных');
        } finally {
            setLoading(false);
        }
    };

    // Функция для получения информации о выбранной локации
    const handleLocationClick = async (locationId: string) => {
        setLoading(true);
        setError('');

        try {
            const response = await axios.get<LocationInfoDTO>(`https://api.example.com/locations/${locationId}`);
            setSelectedLocation(response.data); // Устанавливаем данные выбранной локации
        } catch (error) {
            setError('Ошибка при получении информации о локации');
        } finally {
            setLoading(false);
        }
    };

    // Функция для закрытия деталей локации
    const closeLocationDetails = () => {
        setSelectedLocation(null); // Закрываем выбранную локацию
    };

    return (
        <div>
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
            {error && <p style={{ color: 'red' }}>{error}</p>}

            {/* Если выбрана локация, показываем ее детали */}
            {selectedLocation ? (
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
                                <img key={index} src={image} alt={`Изображение ${index + 1}`} width="200" />
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
                    {selectedLocation.isClosed && <p style={{ color: 'red' }}>Место закрыто</p>}
                </div>
            ) : (
                <ul>
                    {results.map((location, index) => (
                        <li key={index} onClick={() => handleLocationClick(location.lat.toString())}>
                            <h3>{location.name}</h3>
                            <p>Адрес: {location.address}</p>
                            <p>Координаты: {location.lat}, {location.lng}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default Search;
