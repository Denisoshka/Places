import {API_URL, SearchRadius} from './config.js';
import {YMap, YMapDefaultFeaturesLayer, YMapLocationRequest} from 'ymaps3';

ymaps3.strictMode = true;

const ResultsDiv = document.getElementById('resultView') as HTMLDivElement;
const ResultsElement = document.getElementById('results') as HTMLElement;
const PlaceImgContainer = document.getElementById('placeImgContainer') as HTMLElement;
const PlaceInfoContainer = document.getElementById('placeInfoContainer') as HTMLElement;

const PlaceMapContainer = document.getElementById('mapContainer') as HTMLElement;
const SearchPlaceApiURL = `${API_URL}/places/searchPlace`;
const SearchPopulatedPlacesApiURL = `${API_URL}/places/populatedPlacesInRadius`
const SearchPlaceInfo = `${API_URL}/places/placeInfo`

let Map: YMap;
let MarkersLayer: YMapDefaultFeaturesLayer;

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

async function fetchPlaceInfo(xid: string): Promise<void> {
  try {
    const url = `${SearchPlaceInfo}?xid=${xid}`;
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error()
    }
    const details: PlaceInfoDTO = await response.json();
    await showPlaceInfoDetails(details);
  } catch (err: any) {
    ResultsDiv.innerText = `Произошла ошибка при получении деталей: `;
    if (err instanceof Error) {
      ResultsDiv.innerText += err.message;
    }
  }
}

function refreshMap(lng: number, lon: number): void {

}

async function showPlaceInfoDetails(info: PlaceInfoDTO): Promise<void> {
  let img: HTMLImageElement
  try {
    PlaceImgContainer.innerHTML = '';
    PlaceInfoContainer.textContent = info.desc
    img = await fetchImage(info.image);
    img.alt = "downloaded image";
  } catch (error: any) {
    img = document.createElement('img');
  }
  img.id = "placeImage";
  PlaceImgContainer.appendChild(img);
}

async function fetchImage(url: string): Promise<HTMLImageElement> {
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`Error: ${response.status}`);
  }
  const blob = await response.blob();
  const imgUrl = URL.createObjectURL(blob);

  const img = document.createElement('img');
  img.src = imgUrl;
  return img;
}

async function fetchPlacesInRadius(lat: number, lon: number, radius: number): Promise<void> {
  try {
    const url = `${SearchPopulatedPlacesApiURL}?lat=${lat}&lan=${lon}&r=${radius}`;
    const response = await fetch(url);
    if (!response.ok) {
      throw new Error(`Error: ${response.status}`);
    }
    const details: NearByPlaceInfoDTO[] = await response.json();
    displayPlacesInRadius(details);


  } catch (error: any) {
    ResultsDiv.innerText = `An error occurred while retrieving details: `;
    if (error instanceof Error) {
      ResultsDiv.innerText += error.message
    }
  }
}

function displayPlacesInRadius(places: NearByPlaceInfoDTO[]): void {
  Map.removeChild(MarkersLayer)
  MarkersLayer = new ymaps3.YMapDefaultFeaturesLayer({zIndex: 1000})

  places.forEach(location => {
    const content = document.createElement('div');
    content.className = "customMarker";
    content.textContent = location.name;

    const marker = new ymaps3.YMapMarker({
      coordinates: [location.lat, location.lon],
    }, content);
    Map.addChild(marker);
  });
}

export async function initMap(): Promise<void> {
  const LOCATION: YMapLocationRequest = {
    center: [54.842993, 83.090845],
    zoom: 9
  };
  await ymaps3.ready;
  Map = new YMap(
    document.getElementById('map') as HTMLElement,
    {location: LOCATION}
  );
  MarkersLayer = new YMapDefaultFeaturesLayer({zIndex: 1000})

  Map.addChild(MarkersLayer);
}

export async function init(): Promise<void> {
  await initMap();
}
