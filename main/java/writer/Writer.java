package writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class Writer {

    private String city;

    public Writer(String city) {
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void writeStringToFile(String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("src/main/java/requester/%s.txt", this.city), true));
        if (string != null && !string.equals("null")) {
            writer.write(string);
            writer.newLine();
        }
        writer.close();
    }

}
