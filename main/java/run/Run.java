package run;

import requestermanager.RequesterManager;

import java.io.IOException;
import java.text.ParseException;

public class Run {

    public static void main(String[] args) throws IOException, ParseException {
        RequesterManager manager = new RequesterManager();
        manager.readFromFile();
        System.out.println(manager.getRequesters().get(0).readFromFile("src/main/java/requester/weather.json"));

    }

}
