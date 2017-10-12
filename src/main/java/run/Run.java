package run;

import Api.WeatherApi;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Run {

    public static void main(String[] args) throws IOException, ParseException {
        WeatherApi api = new WeatherApi("Tallinn", "EE");
        System.out.println(String.format("lat: %f, lon: %f", api.getCordinates()[0], api.getCordinates()[1]));
        System.out.println(api.getHighestTemperatures(api.getThreeDaysForecast()));
        System.out.println(api.getLowestTemperatures(api.getThreeDaysForecast()).size());
        System.out.println(api.getThreeDaysForecast().get(api.getThreeDaysForecast().length() - 1));
    }
}
