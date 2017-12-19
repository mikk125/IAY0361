package currentdatarequester;

import org.json.JSONObject;
import writer.Writer;

import java.io.IOException;

public class CurrentDataRequester {

    private JSONObject jsonObject;
    private boolean writeDataToFile;
    private Writer writer;

    public CurrentDataRequester(JSONObject jsonResult, boolean writeDataToFile, Writer writer) {
        this.writeDataToFile = writeDataToFile;
        this.jsonObject = jsonResult;
        this.writer = writer;
    }

    public Double[] getCordinates() throws IOException {
        double latitude = (double) this.jsonObject.getJSONObject("coord").get("lat");
        double longitude = (double) this.jsonObject.getJSONObject("coord").get("lon");
        if (this.writeDataToFile) {
            this.writer.writeStringToFile(String.format("Lat: %f, lon: %f", latitude, longitude));
        }
        return new Double[] {latitude, longitude};
    }


    public String getCurrentTemperature() throws IOException {
        String str = this.jsonObject.getJSONObject("main").get("temp").toString();
        if (this.writeDataToFile) {
            this.writer.writeStringToFile(String.format("Current temp: %s", str));
        }
        return str;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }
}
