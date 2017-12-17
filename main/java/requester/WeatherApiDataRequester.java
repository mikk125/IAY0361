package requester;

import currentdatarequester.CurrentDataRequester;
import forecast.ForecastDataRequester;
import org.json.*;
import writer.Writer;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class WeatherApiDataRequester {

    private String city;
    private String countryCode;
    private ForecastDataRequester forecastDataRequester;
    private CurrentDataRequester currentDataRequester;

    public WeatherApiDataRequester(String city, String countryCode, boolean writeResultsToFile) throws IOException {
        if (city != null && city.equals("")) {
            String result = this.getCityFromConsole();
            this.city = result.split(",")[0];
            this.countryCode = result.split(",")[1];
        } else {
            this.city = city;
            this.countryCode = countryCode;
        }
        if (this.city != null && this.countryCode != null) {
            this.forecastDataRequester = new ForecastDataRequester(this.getJsonResult("forecast"), writeResultsToFile, new Writer(this.city));
            this.currentDataRequester = new CurrentDataRequester(this.getJsonResult("weather"), writeResultsToFile, new Writer(this.city));
        }
    }

    public String getCity() {
        return this.city;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getCityFromConsole() {
        System.out.println("Enter a city name:");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
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

    public static JSONObject readFromFile(String fileName) throws FileNotFoundException {
        String everything = "";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything += sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(everything);
    }

    public ForecastDataRequester getForecastDataRequester() {
        return this.forecastDataRequester;
    }

    public CurrentDataRequester getCurrentDataRequester() {
        return this.currentDataRequester;
    }
}
