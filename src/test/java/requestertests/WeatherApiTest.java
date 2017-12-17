package requestertests;

import org.json.*;
import org.junit.Before;
import org.junit.Test;
import requester.WeatherApiDataRequester;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class WeatherApiTest {

    private final String EE = "ee";
    private WeatherApiDataRequester api;

    @Before
    public void setup() throws IOException {
        this.api = new WeatherApiDataRequester("Tallinn", this.EE, false, false);
    }

    @Test
    public void jsonResultIsNotEmptyTest() {
        JSONObject jsonResult;
        try {
            jsonResult = this.api.getJsonResult("forecast");
            assertEquals(false, jsonResult.equals(""));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }

    }

    @Test
    public void jsonResultIsNotNull() {
        JSONObject jsonResult;
        try {
            jsonResult = this.api.getJsonResult("forecast");
            assertEquals(false, jsonResult == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctUrlTest() {
        try {
            assertEquals("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn,ee&units=metric&APPID=d7a75eff97fea51d0f48be9fdc73811a", this.api.getUrl("forecast"));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void incorrectInputTest1() throws IOException {
        WeatherApiDataRequester api2 = new WeatherApiDataRequester("Tallinn", null, false, false);
        try {
            assertEquals(null, api2.getUrl("forecast"));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void incorrectInputTest2() throws IOException {
        WeatherApiDataRequester api2 = new WeatherApiDataRequester(null, this.EE, false, false);
        try {
            assertEquals(null, api2.getUrl("forecast"));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctOutputCordinatesTest() {
        try {
            Double[] cordinates = this.api.getCurrentDataRequester().getCordinates();
            assertTrue(cordinates[0] != null && cordinates[1] != null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void cordinatesNotNullTest() {
        try {
            Double[] cordinates = this.api.getCurrentDataRequester().getCordinates();
            assertFalse(cordinates == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void cordinatesHaveTwoElementsTest() {
        try {
            Double[] cordinates = this.api.getCurrentDataRequester().getCordinates();
            assertTrue(cordinates.length == 2);
        } catch (Exception e) {
            fail("Failure cause:" + e.getMessage());
        }


    }

    @Test
    public void nullThreeDaysForecastHighestTempRequestTest() {
        try {
            List highestTemp = this.api.getForecastDataRequester().getHighestTemperatures(null);
            assertEquals(null, highestTemp);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void nullThreeDaysForecastLowestTempRequestTest() {
        try {
            List lowestTemp = this.api.getForecastDataRequester().getLowestTemperatures(null);
            assertEquals(null, lowestTemp);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void notNullThreeDaysForecastHighestTempRequestTest() {
        try {
            List highestTemp = this.api.getForecastDataRequester().getHighestTemperatures(this.api.getForecastDataRequester().getThreeDaysForecast());
            assertFalse(highestTemp == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void notNullThreeDaysForecastLowestTempRequestTest() {
        try {
            List lowestTemp = this.api.getForecastDataRequester().getLowestTemperatures(this.api.getForecastDataRequester().getThreeDaysForecast());
            assertFalse(lowestTemp == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void jsonThreeDaysForecastIsNullTest() throws IOException, ParseException {
        JSONArray threeDaysForecast = this.api.getForecastDataRequester().getThreeDaysForecast();
        assertEquals(false, threeDaysForecast == null);
    }

    @Test
    public void currentTempInRangeTest() throws IOException {
        assertTrue(this.api.getCurrentDataRequester().getCurrentTemperature() <= 35 && this.api.getCurrentDataRequester().getCurrentTemperature() >= -35);
    }

    @Test
    public void readFromPageNotNullTest() throws IOException {
        assertTrue(this.api.readFromPage(this.api.getUrl("forecast")) != null);
    }

    @Test
    public void readFromPageNotEmptyTest() throws IOException {
        assertTrue(!this.api.readFromPage(this.api.getUrl("forecast")).equals(""));
    }

    @Test
    public void addTimeToDateTest() throws IOException, ParseException {
        String currentDate = "2017-10-14 21:45:00";
        assertEquals("2017-10-15 21:45:00", this.api.getForecastDataRequester().addTimeToDate(currentDate, 24));
    }

    @Test
    public void addTimeToDateZeroHoursTest() throws IOException, ParseException {
        String currentDate = "2017-10-14 21:45:00";
        assertEquals(null, this.api.getForecastDataRequester().addTimeToDate(currentDate, 0));
    }

    @Test
    public void addTimeToDateNullCurrentDateTest() throws IOException, ParseException {
        assertEquals(null, this.api.getForecastDataRequester().addTimeToDate(null, 24));
    }

    @Test
    public void addTimeToDateEmptyCurrentDateTest() throws IOException, ParseException {
        assertEquals(null, this.api.getForecastDataRequester().addTimeToDate("", 24));
    }
}