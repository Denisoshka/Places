// Импортируем базовый URL из конфигурации
import {API_URL} from './config.js';

export async function searchLocation(location: string): Promise<void> {
    if (!location) {
        alert('Enter location name');
        return;
    }
    const apiUrl = `${API_URL}/places/searchByPlace?place=${location}`;
    try {
        const response = await fetch(apiUrl);
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
    const resultsElement = document.getElementById('results') as HTMLElement;
    resultsElement.innerHTML = '';

    if (locations.length === 0) {
        resultsElement.innerHTML = '<p>No results found.</p>';
        return;
    }

    locations.forEach((location) => {
        const locationDiv = document.createElement('div');
        locationDiv.className = 'location';
        locationDiv.innerHTML = `
            <h3>${location.name}, ${location.address}</h3>
            <p>Координаты: (${location.lat}, ${location.lng})</p>
        `;
        resultsElement.appendChild(locationDiv);
    });
}
