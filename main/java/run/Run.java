package run;

import requester.WeatherApiDataRequester;
import requestermanager.RequesterManager;

import java.io.IOException;
import java.text.ParseException;

public class Run {

    public static void main(String[] args) throws IOException, ParseException {
        RequesterManager manager = new RequesterManager();
        manager.readFromFile();
        for (WeatherApiDataRequester requester : manager.getRequesters()) {
            System.out.println(requester.getCity());
            System.out.println(requester.getCurrentDataRequester().getCordinates());
            System.out.println(requester.getCurrentDataRequester().getCurrentTemperature());
            System.out.println(requester.getForecastDataRequester().getHighestTemperaturesBy24Hours());
            System.out.println(requester.getForecastDataRequester().getLowestTemperaturesBy24Hours());
        }
    }

}
