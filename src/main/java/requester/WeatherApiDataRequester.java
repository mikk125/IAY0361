package requester;

import currentdatarequester.CurrentDataRequester;
import forecast.ForecastDataRequester;
import org.json.*;
import sun.misc.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class WeatherApiDataRequester {

    private String city;
    private String countryCode;
    private ForecastDataRequester forecastDataRequester;
    private CurrentDataRequester currentDataRequester;

    public WeatherApiDataRequester(String city, String countryCode, boolean getDataFromFile, boolean writeResultsToFile) throws IOException {
        if (!getDataFromFile) {
            if (city != null && city.equals("")) {
                this.city = this.getCityFromConsole().split(",")[0];
                this.countryCode = this.getCityFromConsole().split(",")[1];
            } else {
                this.city = city;
                this.countryCode = countryCode;
            }
        } else {
            String readData = this.readFromFile();
            this.city = readData.split(",")[0];
            this.countryCode = readData.split(",")[1];
        }
        this.forecastDataRequester = new ForecastDataRequester(this.getJsonResult("forecast"), writeResultsToFile);
        this.currentDataRequester = new CurrentDataRequester(this.getJsonResult("weather"), writeResultsToFile);
    }

    public String getCityFromConsole() {
        System.out.println("Enter a city name:");
        Scanner input = new Scanner(System.in);
        String s = input.next();
        return s;


    }

    public String getUrl(String type) {
        if ((type.equals("forecast") || type.equals("weather")) && this.city != null && this.countryCode != null && !this.countryCode.equals("")) {
            return String.format("http://api.openweathermap.org/data/2.5/%s?q=%s,%s&units=metric&APPID=d7a75eff97fea51d0f48be9fdc73811a", type, this.city, this.countryCode);
        }
        return null;
    }

    public JSONObject getJsonResult(String type) throws IOException {
        try {
            return new JSONObject(this.readFromPage(this.getUrl(type)));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String readFromPage(String fileName) throws IOException {
        try {
            String jsonData = "";
            URL oracle = new URL(fileName);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                jsonData += inputLine + "\n";
            in.close();
            return jsonData;
        } catch (MalformedURLException|NullPointerException e) {
            return null;
        }
    }

    public String readFromFile() throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/main/java/requester/input.txt"));
        return in.next();
    }

    public ForecastDataRequester getForecastDataRequester() {
        return this.forecastDataRequester;
    }

    public CurrentDataRequester getCurrentDataRequester() {
        return this.currentDataRequester;
    }
}
