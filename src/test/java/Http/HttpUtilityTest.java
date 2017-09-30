package Http;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class HttpUtilityTest {

    @Test
    public void httpConnectionSuccessfulTest() throws IOException {
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getResponseCode(), 200);
        } catch (IOException ioq) {
            fail("HTTP connection threw error: " + ioq.getLocalizedMessage());
        }
    }

    @Test
    public void httpRequestResultTypeJson() {
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getContentType(), "json");
        } catch (Exception ioq) {
            fail("HTTP connection threw error: " + ioq.getLocalizedMessage());
        }
    }

    @Test
    public void httpRequestResultTypeNotNull() {
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getContentType() != null, true);
        } catch (Exception ioq) {
            fail("HTTP connection threw error: " + ioq.getLocalizedMessage());
        }
    }

    @Test
    public void httpRequestResultTypeNotEmpty() {
        try {
            String requestUrl = "<API REQUEST URL>";
            HttpURLConnection con = HttpUtility.makeHttpGetRequest(requestUrl);
            assertEquals(con.getContentType().equals(""), true);
        } catch (Exception ioq) {
            fail("HTTP connection threw error: " + ioq.getLocalizedMessage());
        }
    }

}