"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const config_1 = require("./config");
function searchLocation() {
    return __awaiter(this, void 0, void 0, function* () {
        const inputElement = document.getElementById('locationInput');
        const locationName = inputElement.value;
        if (!locationName) {
            alert('Введите название локации!');
            return;
        }
        const apiUrl = `${config_1.API_URL}?search=${encodeURIComponent(locationName)}`;
        try {
            const response = yield fetch(apiUrl);
            const data = yield response.json();
            displayResults(data);
        }
        catch (error) {
            console.error('Ошибка при запросе:', error);
            alert('Ошибка при запросе данных');
        }
    });
}
function displayResults(locations) {
    const resultsElement = document.getElementById('results');
    resultsElement.innerHTML = '';
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
//# sourceMappingURL=app.js.map