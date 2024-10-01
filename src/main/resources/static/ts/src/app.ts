import {API_URL, SearchRadius} from './config.js';
import {YMap, YMapLocationRequest} from 'ymaps3';

const ResultsDiv = document.getElementById('resultView') as HTMLDivElement;
const SearchPlaceApiURL = `${API_URL}/places/searchPlace`;
const SearchPopulatedPlacesApiURL = `${API_URL}/places/populatedPlaces`
const ResultsElement = document.getElementById('results') as HTMLElement;
let Map: YMap;

export async function searchLocation(location: string): Promise<void> {
  if (!location) {
    alert('Enter location name');
    return;
  }
  const url = `${SearchPlaceApiURL}?place=${location}`
  try {
    const response = await fetch(url);
    if (!response.ok) {
      console.error(`Error while fetch data: ${response.status}`)
      return;
    }

    const data: Location[] = await response.json();
    displayResults(data);
  } catch (error) {
    console.error('Error while fetch data:', error);
  }
}

function displayResults(locations: Location[]): void {
  ResultsElement.innerHTML = '';

  if (locations.length === 0) {
    ResultsElement.innerHTML = '<p>No results found.</p>';
    return;
  }

  locations.forEach((location) => {
    const locationDiv = document.createElement('div');
    locationDiv.className = 'location';
    locationDiv.innerHTML = `<h3>${location.name}, ${location.address}</h3><p>Координаты: (${location.lat}, ${location.lng})</p>`;
    locationDiv.addEventListener("click",
      () => fetchPlacesInRadius(location.lat, location.lng, SearchRadius)
    )
    ResultsElement.appendChild(locationDiv);
  });
}

async function fetchPlaceInfo(place: string) {

}

async function fetchPlacesInRadius(lat: number, lon: number, radius: number): Promise<void> {
  try {
    const url = `${SearchPopulatedPlacesApiURL}?lat=${lat}&lan=${lon}&r=${radius}`;
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Ошибка: ${response.status}`);
    }
    const details: NearByPlaceInfoDTO[] = await response.json();
    displayPlacesInRadius(details); // Отображаем полученные данные
  } catch (error: any) {
    ResultsDiv.innerText = `Произошла ошибка при получении деталей: ${error.message}`;
  }
}

function displayPlacesInRadius(places: NearByPlaceInfoDTO[]): void {
  places.forEach(location => {
    const content = document.createElement('div');
    content.className = "custom-marker";
    content.textContent = location.name;

    const marker = new ymaps3.YMapMarker({
      coordinates: [location.lat, location.lon],
    }, content);

    Map.addChild(marker)
  });
}

export async function initMap(): Promise<void> {
  const LOCATION: YMapLocationRequest = {
    center: [54.842993, 83.090845],
    zoom: 9
  };
  await ymaps3.ready;
  Map = new ymaps3.YMap(
    document.getElementById('map') as HTMLElement,
    {location: LOCATION}
  );
}
