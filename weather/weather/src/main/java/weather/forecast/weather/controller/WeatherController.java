package weather.forecast.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weather.forecast.weather.model.WeatherForecast;
import weather.forecast.weather.service.WeatherService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<List<WeatherForecast>> getCityForecast(@PathVariable String city) {
        try {
            return ResponseEntity.ok(weatherService.getForecastForCity(city));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/compare")
    public ResponseEntity<Map<String, List<WeatherForecast>>> compareCities(
            @RequestParam String city1,
            @RequestParam String city2) {
        try {
            return ResponseEntity.ok(weatherService.compareForecasts(city1, city2));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
