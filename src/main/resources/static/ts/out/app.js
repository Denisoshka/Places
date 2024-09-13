var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
import { API_URL } from './config.js';
export function searchLocation(location) {
    return __awaiter(this, void 0, void 0, function* () {
        if (!location) {
            alert('Enter location name');
            return;
        }
        const apiUrl = `${API_URL}/places/searchByPlace?place=${location}`;
        try {
            const response = yield fetch(apiUrl);
            if (!response.ok) {
                console.error(`Error while fetch data: ${response.status}`);
                return;
            }
            const data = yield response.json();
            displayResults(data);
        }
        catch (error) {
            console.error('Error while fetch data:', error);
        }
    });
}
function displayResults(locations) {
    const resultsElement = document.getElementById('results');
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
//# sourceMappingURL=app.js.map