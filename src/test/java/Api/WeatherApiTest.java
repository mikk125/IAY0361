package Api;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class WeatherApiTest {

    private String EE = "ee";

    @Test
    public void jsonResultIsNotEmptyTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            String jsonResult = api.getJsonResult();
            assertEquals(jsonResult.equals(""), false);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }

    }

    @Test
    public void jsonResultIsNotNull() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            String jsonResult = api.getJsonResult();
            assertEquals(jsonResult == null, false);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctUrlTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            assertEquals(api.getUrl(), "api.openweathermap.org/data/2.5/forecast?q=Tallinn,ee");
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctUrlTest2() {
        try {
            WeatherApi api = new WeatherApi("Tallinn");
            assertEquals(api.getUrl(), "api.openweathermap.org/data/2.5/forecast?q=Tallinn");
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void incorrectInputTest1() {
        try {
            WeatherApi api = new WeatherApi("", this.EE);
            assertEquals(api.getUrl(), null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void incorrectInputTest2() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", null);
            assertEquals(api.getUrl(), null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void correctOutputCordinatesTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            String cordinates = api.getCordinates();
            assertTrue(cordinates.matches("^\\d{3}:\\d{3}$"));
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void highestTemperatureGreaterThanLowest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            List threeDaysForecast = api.getThreeDaysForecast();
            Date date = new Date();
            double highestTemp = api.getHighestTemperature(threeDaysForecast, date);
            double lowestTemp = api.getLowestTemperature(threeDaysForecast, date);
            assertTrue(highestTemp > lowestTemp);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void nullDateInHighestTempRequestTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            List threeDaysForecast = api.getThreeDaysForecast();
            double highestTemp = api.getHighestTemperature(threeDaysForecast, null);
            assertEquals(highestTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void nullDateInLowestTempRequestTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            List threeDaysForecast = api.getThreeDaysForecast();
            double lowestTemp = api.getLowestTemperature(threeDaysForecast,null);
            assertEquals(lowestTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void nullThreeDaysForecastHighestTempRequestTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            Date date = new Date();
            double highestTemp = api.getHighestTemperature(null, date);
            assertEquals(highestTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void nullThreeDaysForecastLowestTempRequestTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            Date date = new Date();
            double lowestTemp = api.getLowestTemperature(null, date);
            assertEquals(lowestTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void emptyThreeDaysForecastHighestTempRequestTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            List threeDaysForecast = new ArrayList();
            Date date = new Date();
            double highestTemp = api.getHighestTemperature(threeDaysForecast, date);
            assertEquals(highestTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void emptyThreeDaysForecastLowestTempRequestTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            List threeDaysForecast = new ArrayList();
            Date date = new Date();
            double lowestTemp = api.getLowestTemperature(threeDaysForecast, date);
            assertEquals(lowestTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void currentTempNotNullTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            double currentTemp = api.getCurrentTemperature();
            assertEquals(currentTemp, null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

    @Test
    public void jsonThreeDaysForecastLengthTest() {
        WeatherApi api = new WeatherApi("Tallinn", this.EE);
        List threeDaysForecast = api.getThreeDaysForecast();
        assertEquals(threeDaysForecast.size(), 3);
    }

    @Test
    public void jsonThreeDaysForecastIsNullTest() {
        WeatherApi api = new WeatherApi("Tallinn", this.EE);
        List threeDaysForecast = api.getThreeDaysForecast();
        assertEquals(threeDaysForecast == null, false);
    }

    @Test
    public void cordinatesNotNullTest() {
        try {
            WeatherApi api = new WeatherApi("Tallinn", this.EE);
            String cordinates = api.getCordinates();
            assertFalse(cordinates == null);
        } catch (Exception e) {
            fail("Failure cause: " + e.getMessage());
        }
    }

}