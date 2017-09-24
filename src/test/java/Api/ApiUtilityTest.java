package Api;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class ApiUtilityTest {

    @Test
    public void testApiReturnsCorrectCordinates() throws IOException {
        ApiUtility api = new ApiUtility();
        assertEquals(api.apiReturnsCorrectCordinates(), true);
    }

    @Test
    public void testApiReturnsCorrectTemperatures() throws IOException {
        ApiUtility api = new ApiUtility();
        assertEquals(api.apiReturnsCorrectMomentTemperature(), true);
    }

    @Test
    public void testApiReturnsCorrectMomentTemperature() throws IOException {
        ApiUtility api = new ApiUtility();
        assertEquals(api.apiReturnsCorrectMomentTemperature(), true);
    }


    @Test
    public void testApiHighestAndLowestTempComparison() throws IOException {
        ApiUtility api = new ApiUtility();
        assertEquals(api.apiHighestAndLowestTempComparison(), true);
    }

    @Test
    public void testApiDataRangeIsThreeHours() throws IOException {
        ApiUtility api = new ApiUtility();
        assertEquals(api.apiDataRangeIsThreeHours(), true);
    }

}