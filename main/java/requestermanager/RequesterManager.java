package requestermanager;

import requester.WeatherApiDataRequester;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RequesterManager {

    private List<WeatherApiDataRequester> requesters = new ArrayList<>();

    public void readFromFile() throws IOException {
        Scanner in = new Scanner(new FileReader("src/main/java/requestermanager/input.txt"));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            this.requesters.add(
                    new WeatherApiDataRequester(
                        line.split(",")[0],
                            line.split(",")[1],
                            true
                    )
            );
        }
    }

    public List<WeatherApiDataRequester> getRequesters() {
        return this.requesters;
    }

    public WeatherApiDataRequester getRequesterByData(String city, String cityCode) {
        for (WeatherApiDataRequester requester : this.requesters) {
            if (requester.getCity().equals(city) && requester.getCountryCode().equals(cityCode)) {
                return requester;
            }
        }
        return null;
    }
}
