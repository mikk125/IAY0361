package Api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherApi {

    private String city;
    private String countryCode;

    public WeatherApi(String city) {
        this.city = city;
    }

    public WeatherApi(String city, String countryCode) {
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getUrl() {
        return null;
    }

    public String getJsonResult() {
        return null;
    }

    public String getCordinates() {
        return null;
    }

    public List getThreeDaysForecast() {
        return new ArrayList();
    }

    public double getHighestTemperature(List threeDaysForecast, Date date) {
        return Double.parseDouble(null);
    }

    public double getLowestTemperature(List threeDaysForecast, Date date) {
        return Double.parseDouble(null);
    }

    public double getCurrentTemperature() {
        return Double.parseDouble(null);
    }
}
