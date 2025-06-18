package weather.forecast.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import weather.forecast.weather.model.WeatherForecast;


import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

   
    private static final Map<String, double[]> CITY_COORDINATES = Map.of(
            "mumbai", new double[]{19.0760, 72.8777},
            "delhi", new double[]{28.6139, 77.2090},
            "bangalore", new double[]{12.9716, 77.5946},
            "chennai", new double[]{13.0827, 80.2707}
    );

    public List<WeatherForecast> getForecastForCity(String city) {
        city = city.trim().toLowerCase();

        if (!CITY_COORDINATES.containsKey(city)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City not supported: " + city);
        }

        double[] coords = CITY_COORDINATES.get(city);

        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,windspeed_10m_max&timezone=auto",
                coords[0], coords[1]);

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode daily = response.getBody().get("daily");

        List<WeatherForecast> forecasts = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            forecasts.add(new WeatherForecast(
                    LocalDate.parse(daily.get("time").get(i).asText()),
                    daily.get("temperature_2m_max").get(i).asDouble(),
                    daily.get("temperature_2m_min").get(i).asDouble(),
                    daily.get("precipitation_sum").get(i).asDouble(),
                    daily.get("windspeed_10m_max").get(i).asDouble()
            ));
        }

        return forecasts;
    }

    public Map<String, List<WeatherForecast>> compareForecasts(String city1, String city2) {
        Map<String, List<WeatherForecast>> result = new HashMap<>();
        result.put(city1, getForecastForCity(city1));
        result.put(city2, getForecastForCity(city2));
        return result;
    }
}
