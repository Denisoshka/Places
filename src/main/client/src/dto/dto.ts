export interface LocationDTO {
  lat: number;
  lng: number;
  name: string;
  address: string;
}

export interface LocationInfoDTO {
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

export interface PlaceInfoDTO {
  name: string;
  shortName: string;
  shortDescription: string;
  description: string;
  address: string;
  url: string;
  phone: string[];
  images: string[];
  categories: string[];
  tags: string[];
  isClosed: boolean;
}

export interface WeatherDTO {
  lon: number;          // Долгота
  lat: number;          // Широта
  weather: Weather[];   // Массив объектов Weather
  main: Main;           // Объект Main
  visibility: number;   // Видимость
  wind: Wind;           // Объект Wind
  rainHeight: number;  // Высота дождя (необязательно)
  snowHeight: number;  // Высота снега (необязательно)
  cloudiness: number;   // Облачность
  timezone: number;     // Часовой пояс
}

export interface Weather {
  main: string;         // Основное описание погоды
  description: string;  // Подробное описание погоды
}

export interface Main {
  temp: number;         // Температура
  feelsLike: number;    // Ощущаемая температура
  tempMin: number;      // Минимальная температура
  tempMax: number;      // Максимальная температура
  pressure: number;     // Давление
  humidity: number;     // Влажность
  seaLevel: number;    // Давление на уровне моря (необязательно)
  grndLevel: number;   // Давление на уровне земли (необязательно)
}

export interface Wind {
  speed: number;        // Скорость ветра
  deg: number;          // Направление ветра в градусах
  gust: number;        // Порывы ветра (необязательно)
}

