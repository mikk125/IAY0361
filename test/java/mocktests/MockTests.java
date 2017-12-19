package mocktests;

import currentdatarequester.CurrentDataRequester;
import forecast.ForecastDataRequester;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import requester.WeatherApiDataRequester;
import writer.Writer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import static org.junit.Assert.assertTrue;

public class MockTests {

    private WeatherApiDataRequester weatherApiDataRequesterMockObj;
    private JSONObject weatherResponse;
    private JSONObject forecastResponse;
    private Writer mockedFileWriter;

    @Before
    public void setup() throws IOException {
        this.mockedFileWriter = Mockito.mock(Writer.class);
        this.weatherApiDataRequesterMockObj = Mockito.mock(WeatherApiDataRequester.class);
        this.weatherResponse = WeatherApiDataRequester.readFromFile("src/main/java/requester/weather.json");
        this.forecastResponse = WeatherApiDataRequester.readFromFile("src/main/java/requester/forecast.json");
    }

    @Test
    public void createNewFileTest() throws IOException {
        this.mockedFileWriter.setCity("Tallinn");
        this.mockedFileWriter.writeStringToFile(Mockito.anyString());
        assertTrue(new File("src/main/java/requester").list().length > 0);
    }

    @Test
    public void currentWeatherGetJsonResult() throws IOException {
        Mockito.when(this.weatherApiDataRequesterMockObj.getJsonResult("weather")).thenReturn(this.weatherResponse);
        CurrentDataRequester currentDataRequester = new CurrentDataRequester(this.weatherApiDataRequesterMockObj.getJsonResult("weather"), true, this.mockedFileWriter);
        assertTrue(currentDataRequester.getJsonObject() == this.weatherResponse);
    }

    @Test
    public void currentForecastGetJsonResult() throws IOException {
        Mockito.when(this.weatherApiDataRequesterMockObj.getJsonResult("forecast")).thenReturn(this.forecastResponse);
        ForecastDataRequester forecastDataRequester = new ForecastDataRequester(this.weatherApiDataRequesterMockObj.getJsonResult("forecast"), true, this.mockedFileWriter);
        assertTrue(forecastDataRequester.getJsonObject() == this.forecastResponse);
    }

    @Test
    public void currentWeatherAppWritesToFile() throws IOException {
        CurrentDataRequester currentDataRequester = new CurrentDataRequester(this.weatherResponse, true, this.mockedFileWriter);
        currentDataRequester.getCordinates();
        Mockito.verify(this.mockedFileWriter, Mockito.times(1)).writeStringToFile(Mockito.anyString());
    }

    @Test
    public void forecastWeatherAppWritesToFile() throws IOException, ParseException {
        ForecastDataRequester forecastDataRequester = new ForecastDataRequester(this.forecastResponse, true, this.mockedFileWriter);
        forecastDataRequester.getHighestTemperatures(forecastDataRequester.getThreeDaysForecast());
        Mockito.verify(this.mockedFileWriter, Mockito.times(1)).writeStringToFile(Mockito.anyString());
    }


}
