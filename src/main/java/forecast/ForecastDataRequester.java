package forecast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ForecastDataRequester {

    private JSONObject jsonObject;
    private boolean writeDataToFile;

    public ForecastDataRequester(JSONObject jsonObject, boolean writeDataToFile) {
        this.writeDataToFile = writeDataToFile;
        this.jsonObject = jsonObject;
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

    public List getHighestTemperatures(JSONArray threeDaysForecast) throws FileNotFoundException {
        if (threeDaysForecast == null || threeDaysForecast.length() == 0) {
            return null;
        }
        List<Object> highestTemperatures = new ArrayList<>();
        for (int i = 0; i < threeDaysForecast.length(); i++) {
            highestTemperatures.add(threeDaysForecast.getJSONObject(i).get("temp_max"));
        }
        if (this.writeDataToFile) {
            System.setOut(new PrintStream(new FileOutputStream("src/main/java/requester/output.txt", true)));
            System.out.println(String.format("Highest temperatures: %s", highestTemperatures));
        }
        return highestTemperatures;
    }

    public List getLowestTemperatures(JSONArray threeDaysForecast) throws FileNotFoundException {
        if (threeDaysForecast == null || threeDaysForecast.length() == 0) {
            return null;
        }
        List<Object> lowestTemperatures = new ArrayList<>();
        for (int i = 0; i < threeDaysForecast.length(); i++) {
            lowestTemperatures.add(threeDaysForecast.getJSONObject(i).get("temp_max"));
        }
        if (this.writeDataToFile) {
            System.setOut(new PrintStream(new FileOutputStream("src/main/java/requester/output.txt", true)));
            System.out.println(String.format("Highest temperatures: %s", lowestTemperatures));
        }
        return lowestTemperatures;
    }
}
