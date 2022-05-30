import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class WriteFile {
    public void write(String data) {
        try {
            FileWriter fw = new FileWriter("Record.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (IOException error) {
            System.out.println("An error occurred.");
            error.printStackTrace();
        }
    }
}
