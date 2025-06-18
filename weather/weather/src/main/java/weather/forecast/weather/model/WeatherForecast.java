package weather.forecast.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecast {
    private LocalDate date;
    private double temperatureMax;
    private double temperatureMin;
    private double precipitation;
    private double windSpeed;  // bonus
}
