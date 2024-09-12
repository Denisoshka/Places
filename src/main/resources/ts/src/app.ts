// Импортируем базовый URL из конфигурации
import {API_URL} from './config';

async function searchLocation(): Promise<void> {
    const inputElement = document.getElementById('locationInput') as HTMLInputElement;
    const locationName = inputElement.value;

    if (!locationName) {
        alert('Введите название локации!');
        return;
    }

    const apiUrl = `${API_URL}?search=${encodeURIComponent(locationName)}`;

    try {
        const response = await fetch(apiUrl);
        const data: Location[] = await response.json();
        displayResults(data);
    } catch (error) {
        console.error('Ошибка при запросе:', error);
        alert('Ошибка при запросе данных');
    }
}

function displayResults(locations: Location[]): void {
    const resultsElement = document.getElementById('results') as HTMLElement;
    resultsElement.innerHTML = ''; // Очистка предыдущих результатов

    if (locations.length === 0) {
        resultsElement.innerHTML = '<p>Ничего не найдено.</p>';
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
