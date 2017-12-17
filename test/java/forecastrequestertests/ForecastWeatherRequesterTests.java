package forecastrequestertests;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import requester.WeatherApiDataRequester;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class ForecastWeatherRequesterTests {

    private final String EE = "ee";
    private WeatherApiDataRequester api;

    @Before
    public void setup() throws IOException {
        this.api = new WeatherApiDataRequester("Tallinn", this.EE, false);
    }

    @Test
    public void nullThreeDaysForecastHighestTempRequestTest() throws IOException {
        List highestTemp = this.api.getForecastDataRequester().getHighestTemperatures(null);
        assertEquals(null, highestTemp);
    }

    @Test
    public void nullThreeDaysForecastLowestTempRequestTest() throws IOException {
        List lowestTemp = this.api.getForecastDataRequester().getLowestTemperatures(null);
        assertEquals(null, lowestTemp);
    }

    @Test
    public void notNullThreeDaysForecastHighestTempRequestTest() throws IOException, ParseException {
        List highestTemp = this.api.getForecastDataRequester().getHighestTemperatures(this.api.getForecastDataRequester().getThreeDaysForecast());
        assertFalse(highestTemp == null);
    }

    @Test
    public void notNullThreeDaysForecastLowestTempRequestTest() throws IOException, ParseException {
        List lowestTemp = this.api.getForecastDataRequester().getLowestTemperatures(this.api.getForecastDataRequester().getThreeDaysForecast());
        assertFalse(lowestTemp == null);
    }

    @Test
    public void jsonThreeDaysForecastIsNullTest() throws IOException, ParseException {
        JSONArray threeDaysForecast = this.api.getForecastDataRequester().getThreeDaysForecast();
        assertEquals(false, threeDaysForecast == null);
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
