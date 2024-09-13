import { searchLocation } from './app.js';

document.getElementById("search")?.addEventListener("click", async () => {
    const query = (document.getElementById("locationInput") as HTMLInputElement).value;
    if (query) {
        await searchLocation(query);
    }
});
