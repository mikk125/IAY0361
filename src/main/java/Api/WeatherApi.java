package Api;

import org.json.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeatherApi {

    private String city;
    private String countryCode;

    public WeatherApi(String city, String countryCode) {
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getUrl() {
        if (this.city != null && this.countryCode != null && !this.city.equals("") && !this.countryCode.equals("")) {
            return "http://api.openweathermap.org/data/2.5/forecast?id=588409&APPID=d7a75eff97fea51d0f48be9fdc73811a&units=metric";
        }
        return null;
    }

    public JSONObject getJsonResult() throws IOException {

        return new JSONObject(this.readFromPage());
    }

    public String readFromPage() throws IOException {
        String jsonData = "";
        URL oracle = new URL(this.getUrl());
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            jsonData += inputLine + "\n";
        in.close();
        return jsonData;
    }

    public Double[] getCordinates() throws IOException {
        double latitude = (double) this.getJsonResult().getJSONObject("city").getJSONObject("coord").get("lat");
        double longitude = (double) this.getJsonResult().getJSONObject("city").getJSONObject("coord").get("lon");

        return new Double[] {latitude, longitude};
    }

    public JSONArray getThreeDaysForecast() throws IOException, ParseException {
        JSONArray totalListOfWeather = this.getJsonResult().getJSONArray("list");
        JSONArray threeDaysForecast = new JSONArray();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = (String) totalListOfWeather.getJSONObject(0).get("dt_txt");
        String dateInThreeDaysTime = this.addTimeToDate(currentDate, 3 * 24 + 3);
        int i = 0;
        while (sdf.parse((String) totalListOfWeather.getJSONObject(i).get("dt_txt")).before(sdf.parse(dateInThreeDaysTime))) {
            threeDaysForecast.put(totalListOfWeather.getJSONObject(i).get("main"));
            i++;
        }
        return threeDaysForecast;

    }

    public String addTimeToDate(String currentDate, int numberOfHoursToAdd) throws ParseException, IOException {
        if (currentDate != null && !currentDate.equals("") && numberOfHoursToAdd > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int i = 0;
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(currentDate));
            c.add(Calendar.HOUR_OF_DAY, numberOfHoursToAdd);
            return sdf.format(c.getTime());
        }
        return null;
    }

    public List getHighestTemperatures(JSONArray threeDaysForecast) {
        if (threeDaysForecast == null || threeDaysForecast.length() == 0) {
            return null;
        }
        List<Double> highestTemperatures = new ArrayList<>();
        for (int i = 0; i < threeDaysForecast.length(); i++) {
            highestTemperatures.add((Double) threeDaysForecast.getJSONObject(i).get("temp_max"));
        }
        return highestTemperatures;
    }

    public List getLowestTemperatures(JSONArray threeDaysForecast) {
        if (threeDaysForecast == null || threeDaysForecast.length() == 0) {
            return null;
        }
        List<Double> lowestTemperatures = new ArrayList<>();
        for (int i = 0; i < threeDaysForecast.length(); i++) {
            lowestTemperatures.add((Double) threeDaysForecast.getJSONObject(i).get("temp_max"));
        }
        return lowestTemperatures;
    }

    public double getCurrentTemperature() throws IOException {
        return (double) this.getJsonResult().getJSONArray("list").getJSONObject(0).getJSONObject("main").get("temp");
    }
}
