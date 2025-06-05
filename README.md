# Nightsky Project

**Nightsky** is a Java Swing desktop application that displays the positions of planets in the sky for a given location and time. It uses geocoding and astronomical data APIs to retrieve and render accurate planetary positions on a graphical panel.

## Features

- Enter a location and fetch its geographic coordinates via a geocoding API.
- Retrieve real-time astronomical data (altitude and azimuth) for visible planets.
- Graphically display the current night sky on a custom-drawn Swing panel.
- Responsive UI powered by RxJava for asynchronous API calls.
- API key management and protection.

---

## Technologies Used

- **Java Swing**: GUI framework for rendering the night sky
- **RxJava 3**: For asynchronous API handling
- **Retrofit**: For making REST API requests
- **AstronomyAPI**: For planetary positions https://docs.astronomyapi.com
- **OpenWeatherMapAPI**: To convert place names into latitude/longitude https://openweathermap.org/api/geocoding-api
- **Gradle**: Build tool 
- **GitHub**: Version control and evaluation

---
## How It Works

1. The user types a location into the input box.
2. The app sends a request to a geocoding service to get latitude and longitude.
3. Using these coordinates, the app makes a second API call to fetch the positions of planets.
4. The GUI draws the planets at their correct locations in the sky using their azimuth and altitude values.
---
