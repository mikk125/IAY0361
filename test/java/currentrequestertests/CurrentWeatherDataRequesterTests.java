package currentrequestertests;

import org.junit.Before;
import org.junit.Test;
import requester.WeatherApiDataRequester;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CurrentWeatherDataRequesterTests {

    private WeatherApiDataRequester api;
    private final String EE = "ee";
    @Before
    public void setup() throws IOException {
        this.api = new WeatherApiDataRequester("Tallinn", this.EE, false);
    }

    @Test
    public void correctOutputCordinatesTest() throws IOException {
        Double[] cordinates = this.api.getCurrentDataRequester().getCordinates();
        assertTrue(cordinates[0] != null && cordinates[1] != null);
    }

    @Test
    public void cordinatesNotNullTest() throws IOException {
        Double[] cordinates = this.api.getCurrentDataRequester().getCordinates();
        assertFalse(cordinates == null);
    }

    @Test
    public void cordinatesHaveTwoElementsTest() throws IOException {
        Double[] cordinates = this.api.getCurrentDataRequester().getCordinates();
        assertTrue(cordinates.length == 2);
    }

    @Test
    public void currentTempInRangeTest() throws IOException {
        assertTrue(Integer.parseInt(this.api.getCurrentDataRequester().getCurrentTemperature()) <= 35 && Integer.parseInt(this.api.getCurrentDataRequester().getCurrentTemperature()) >= -35);
    }
}
