package mocktests;

import currentdatarequester.CurrentDataRequester;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import requester.WeatherApiDataRequester;
import writer.Writer;
import java.io.IOException;

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
    public void correctResultForForecast() throws IOException {
        Mockito.when(this.weatherApiDataRequesterMockObj.getJsonResult("forecast")).thenReturn(this.forecastResponse);
    }

    @Test
    public void correctResultForWeather() throws IOException {
        Mockito.when(this.weatherApiDataRequesterMockObj.getJsonResult("weather")).thenReturn(this.weatherResponse);
    }

    @Test
    public void correctUrlForForecast() {
        Mockito.when(this.weatherApiDataRequesterMockObj.getUrl("forecast")).thenReturn("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn,ee&units=metric&APPID=d7a75eff97fea51d0f48be9fdc73811a");
    }

    @Test
    public void correctUrlForWeather() {
        Mockito.when(this.weatherApiDataRequesterMockObj.getUrl("weather")).thenReturn("http://api.openweathermap.org/data/2.5/weather?q=Tallinn,ee&units=metric&APPID=d7a75eff97fea51d0f48be9fdc73811a");
    }

    @Test
    public void hasFile() throws IOException {
        this.mockedFileWriter.setCity("Kehtna");

        Mockito.doNothing().when(this.mockedFileWriter).writeStringToFile(Mockito.anyString());
    }
}
