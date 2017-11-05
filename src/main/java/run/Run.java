package run;


import requester.WeatherApiDataRequester;

import java.io.IOException;
import java.text.ParseException;

public class Run {

    public static void main(String[] args) throws IOException, ParseException {
        WeatherApiDataRequester api = new WeatherApiDataRequester("", "ee", true, false);
        System.out.println(api.getUrl("forecast"));
    }

}
