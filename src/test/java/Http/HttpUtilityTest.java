package Http;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class HttpUtilityTest {

    @Test
    public void testHttpSuccessful() throws IOException {
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getResponseCode(), 200);
        } catch (IOException ioq) {
            fail("HTTP connection threw error:" + ioq.getLocalizedMessage());
        }

    }

    @Test
    public void testUrlNotNull() {
        assertEquals(HttpUtility.urlNotNull("http://neti.ee"), true);
    }

    @Test
    public void testUrlNotEmpty() {
        assertEquals(HttpUtility.urlNotEmpty(""), true);
    }

    @Test
    public void testUrlCorrectStructure() {
        assertEquals(HttpUtility.urlCorrectStructure("api.openweathermap.org/data/2.5/forecast?q=London,us"),true);
    }

    @Test
    public void testHttpCorrectContentType() throws IOException {
        String requestUrl = "<API REQUEST URL>";
        HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
        assertEquals(con.getContentType(), "json");
    }

    @Test
    public void testHttpContentNotNull() throws IOException {
        String requestUrl = "<API REQUEST URL>";
        HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
        assertNotEquals(con.getInputStream(), null);
    }

    @Test
    public void testHttpContentNotEmpty() throws IOException {
        String requestUrl = "<API REQUEST URL>";
        HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
        assertNotEquals(con.getInputStream(), "");
    }

    @Test
    public void testJsonFileCanBeDownloaded() throws IOException {
        HttpUtility http = new HttpUtility();
        assertEquals(http.jsonFileCanBeDownloaded(), true);
    }

    @Test
    public void testJsonFileContainsNecessaryData() throws IOException {
        HttpUtility http = new HttpUtility();
        assertEquals(http.jsonFileContainsNecessaryData(), true);
    }


}