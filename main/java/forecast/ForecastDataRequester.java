package forecast;

import org.json.JSONArray;
import org.json.JSONObject;
import writer.Writer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class ForecastDataRequester {

    private JSONObject jsonObject;
    private boolean writeDataToFile;
    private Writer writer;

    public ForecastDataRequester(JSONObject jsonObject, boolean writeDataToFile, Writer writer) {
        this.writeDataToFile = writeDataToFile;
        this.jsonObject = jsonObject;
        this.writer = writer;
    }

    public JSONArray getThreeDaysForecast() throws IOException, ParseException {
        JSONArray totalListOfWeather = this.jsonObject.getJSONArray("list");
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
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(currentDate));
            c.add(Calendar.HOUR_OF_DAY, numberOfHoursToAdd);
            return sdf.format(c.getTime());
        }
        return null;
    }

    public List getHighestTemperatures(JSONArray threeDaysForecast) throws IOException {
        if (threeDaysForecast == null || threeDaysForecast.length() == 0) {
            return null;
        }
        List<Object> highestTemperatures = new ArrayList<>();
        for (int i = 0; i < threeDaysForecast.length(); i++) {
            highestTemperatures.add(threeDaysForecast.getJSONObject(i).get("temp_max"));
        }
        if (this.writeDataToFile) {
            this.writer.writeStringToFile(String.format("Highest temperatures: %s", highestTemperatures));
        }
        return highestTemperatures;
    }

    public HashMap getLowestTemperaturesBy24Hours() throws IOException, ParseException {
        HashMap lowestHashMap = new HashMap();
        List<Double> list = new ArrayList();
        List lowestList = this.getLowestTemperatures(this.getThreeDaysForecast());
        int day = 1;
        for (int i = 0; i < lowestList.size(); i++) {
            list.add(Double.valueOf(String.valueOf(lowestList.get(i))));
            if (i == 7 || i == 15 || i == 23) {
                lowestHashMap.put(day, this.getMin(list));
                day++;
                list = new ArrayList<>();
            }
        }
        if (this.writeDataToFile) {
            this.writer.writeStringToFile(String.format("%dst day - %f", 1, lowestHashMap.get(1)));
            this.writer.writeStringToFile(String.format("%dnd day - %f", 2, lowestHashMap.get(2)));
            this.writer.writeStringToFile(String.format("%drd day - %f", 3, lowestHashMap.get(3)));
        }
        return lowestHashMap;
    }

    public HashMap getHighestTemperaturesBy24Hours() throws IOException, ParseException {
        HashMap highestHashMap = new HashMap();
        List<Double> list = new ArrayList();
        List highestList = this.getHighestTemperatures(this.getThreeDaysForecast());
        int day = 1;
        for (int i = 0; i < highestList.size(); i++) {
            list.add(Double.valueOf(String.valueOf(highestList.get(i))));
            if (i == 7 || i == 15 || i == 23) {
                highestHashMap.put(day, this.getMax(list));
                day++;
                list = new ArrayList<>();
            }
        }
        if (this.writeDataToFile) {
            this.writer.writeStringToFile(String.format("%dst day - %f", 1, highestHashMap.get(1)));
            this.writer.writeStringToFile(String.format("%dnd day - %f", 2, highestHashMap.get(2)));
            this.writer.writeStringToFile(String.format("%drd day - %f", 3, highestHashMap.get(3)));
        }
        return highestHashMap;
    }

    public Double getMin(List<Double> array) {
        double min = (double) array.get(0);
        for (Double i : array){
            min = min < i ? min : i;
        }
        return min;
    }

    public Double getMax(List<Double> array) {
        double max = (double) array.get(0);
        for (Double i : array){
            max = max > i ? max : i;
        }
        return max;
    }

    public List getLowestTemperatures(JSONArray threeDaysForecast) throws IOException {
        if (threeDaysForecast == null || threeDaysForecast.length() == 0) {
            return null;
        }
        List<Object> lowestTemperatures = new ArrayList<>();
        for (int i = 0; i < threeDaysForecast.length(); i++) {
            lowestTemperatures.add(threeDaysForecast.getJSONObject(i).get("temp_min"));
        }
        if (this.writeDataToFile) {
            this.writer.writeStringToFile(String.format("Lowest temperatures: %s", lowestTemperatures));
        }
        return lowestTemperatures;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
