package currentdatarequester;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class CurrentDataRequester {

    private JSONObject jsonObject;
    private boolean writeDataToFile;

    public CurrentDataRequester(JSONObject jsonResult, boolean writeDataToFile) {
        this.writeDataToFile = writeDataToFile;
        this.jsonObject=jsonResult;
    }

    public Double[] getCordinates() throws IOException {
        double latitude = (double) this.jsonObject.getJSONObject("coord").get("lat");
        double longitude = (double) this.jsonObject.getJSONObject("coord").get("lon");
        if (this.writeDataToFile) {
            System.setOut(new PrintStream(new FileOutputStream("src/main/java/requester/output.txt", true)));
            System.out.println(String.format("Lat: %s, lon: %s", latitude, longitude));
        }
        return new Double[] {latitude, longitude};
    }


    public Integer getCurrentTemperature() throws IOException {
        if (this.writeDataToFile) {
            System.setOut(new PrintStream(new FileOutputStream("src/main/java/requester/output.txt", true)));
            System.out.println(String.format("Current temp: %d", (Integer) this.jsonObject.getJSONObject("main").get("temp")));
        }
        return (Integer) this.jsonObject.getJSONObject("main").get("temp");
    }


}
