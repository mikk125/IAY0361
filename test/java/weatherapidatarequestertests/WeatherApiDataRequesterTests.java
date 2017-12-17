package weatherapidatarequestertests;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import requester.WeatherApiDataRequester;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class WeatherApiDataRequesterTests {

    private final String EE = "ee";
    private WeatherApiDataRequester api;

    @Before
    public void setup() throws IOException {
        this.api = new WeatherApiDataRequester("Tallinn", this.EE, false);
    }

    @Test
    public void jsonResultIsNotEmptyTest() throws IOException {
        JSONObject jsonResult;
        jsonResult = this.api.getJsonResult("forecast");
        assertEquals(false, jsonResult.equals(""));

    }

    @Test
    public void jsonResultIsNotNull() throws IOException {
        JSONObject jsonResult;
        jsonResult = this.api.getJsonResult("forecast");
        assertEquals(false, jsonResult == null);
    }

    @Test
    public void correctUrlTest() {
        assertEquals("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn,ee&units=metric&APPID=d7a75eff97fea51d0f48be9fdc73811a", this.api.getUrl("forecast"));
    }

    @Test
    public void incorrectInputTest1() throws IOException {
        WeatherApiDataRequester api2 = new WeatherApiDataRequester("Tallinn", null,  false);
        assertEquals(null, api2.getUrl("forecast"));
    }

    @Test
    public void incorrectInputTest2() throws IOException {
        WeatherApiDataRequester api2 = new WeatherApiDataRequester(null, this.EE, false);
        assertEquals(null, api2.getUrl("forecast"));
    }

    @Test
    public void readFromPageNotNullTest() throws IOException {
        assertTrue(this.api.readFromPage(this.api.getUrl("forecast")) != null);
    }

    @Test
    public void readFromPageNotEmptyTest() throws IOException {
        assertTrue(!this.api.readFromPage(this.api.getUrl("forecast")).equals(""));
    }
}
