package Api;

import org.json.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class WeatherApiTest {

    private String EE = "ee";
    private WeatherApi api;

    @Before
    public void setup() {
        this.api = new WeatherApi("Tallinn", this.EE);
    }

    @Test
    public void jsonResultIsNotEmptyTest() {
        JSONObject jsonResult;
        try {
            jsonResult = this.api.getJsonResult();
            assertEquals(false, jsonResult.equals(""));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }

    }

    @Test
    public void jsonResultIsNotNull() {
        JSONObject jsonResult;
        try {
            jsonResult = this.api.getJsonResult();
            assertEquals(false, jsonResult == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctUrlTest() {
        try {
            assertEquals("http://api.openweathermap.org/data/2.5/forecast?id=588409&APPID=d7a75eff97fea51d0f48be9fdc73811a&units=metric", this.api.getUrl());
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctUrlTest2() {
        try {
            assertEquals("http://api.openweathermap.org/data/2.5/forecast?id=588409&APPID=d7a75eff97fea51d0f48be9fdc73811a&units=metric", this.api.getUrl());
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void incorrectInputTest1() {
        WeatherApi api2 = new WeatherApi("Tallinn", null);
        try {
            assertEquals(null, api2.getUrl());
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void incorrectInputTest2() {
        WeatherApi api2 = new WeatherApi(null, this.EE);
        try {
            assertEquals(null, api2.getUrl());
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctOutputCordinatesTest() {
        try {
            Double[] cordinates = this.api.getCordinates();
            assertTrue(cordinates[0] != null && cordinates[1] != null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void cordinatesNotNullTest() {
        try {
            Double[] cordinates = this.api.getCordinates();
            assertFalse(cordinates == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void cordinatesHaveTwoElementsTest() {
        try {
            Double[] cordinates = this.api.getCordinates();
            assertTrue(cordinates.length == 2);
        } catch (Exception e) {
            fail("Failure cause:" + e.getMessage());
        }


    }

    @Test
    public void nullThreeDaysForecastHighestTempRequestTest() {
        try {
            List highestTemp = this.api.getHighestTemperatures(null);
            assertEquals(null, highestTemp);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void nullThreeDaysForecastLowestTempRequestTest() {
        try {
            List lowestTemp = this.api.getLowestTemperatures(null);
            assertEquals(null, lowestTemp);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void notNullThreeDaysForecastHighestTempRequestTest() {
        try {
            List highestTemp = this.api.getHighestTemperatures(this.api.getThreeDaysForecast());
            assertFalse(highestTemp == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void notNullThreeDaysForecastLowestTempRequestTest() {
        try {
            List lowestTemp = this.api.getLowestTemperatures(this.api.getThreeDaysForecast());
            assertFalse(lowestTemp == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void jsonThreeDaysForecastLengthTest() throws IOException, ParseException {
        JSONArray threeDaysForecast = this.api.getThreeDaysForecast();
        assertEquals(threeDaysForecast.length(), 25);
    }

    @Test
    public void jsonThreeDaysForecastIsNullTest() throws IOException, ParseException {
        JSONArray threeDaysForecast = this.api.getThreeDaysForecast();
        assertEquals(false, threeDaysForecast == null);
    }

    @Test
    public void currentTempInRangeTest() throws IOException {
        assertTrue(this.api.getCurrentTemperature() <= 35 && this.api.getCurrentTemperature() >= -35);
    }

    @Test
    public void readFromPageNotNullTest() throws IOException {
        assertTrue(this.api.readFromPage() != null);
    }

    @Test
    public void readFromPageNotEmptyTest() throws IOException {
        assertTrue(!this.api.readFromPage().equals(""));
    }

    @Test
    public void addTimeToDateTest() throws IOException, ParseException {
        String currentDate = "2017-10-14 21:45:00";
        assertEquals("2017-10-15 21:45:00", this.api.addTimeToDate(currentDate, 24));
    }

    @Test
    public void addTimeToDateZeroHoursTest() throws IOException, ParseException {
        String currentDate = "2017-10-14 21:45:00";
        assertEquals(null, this.api.addTimeToDate(currentDate, 0));
    }

    @Test
    public void addTimeToDateNullCurrentDateTest() throws IOException, ParseException {
        assertEquals(null, this.api.addTimeToDate(null, 24));
    }

    @Test
    public void addTimeToDateEmptyCurrentDateTest() throws IOException, ParseException {
        assertEquals(null, this.api.addTimeToDate("", 24));
    }
}