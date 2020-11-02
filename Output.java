import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    public void myPrint(ArrayList<Double> ans ) throws IOException {
        FileWriter writer = new FileWriter("./src/output3.txt", true);
        StringBuilder str = new StringBuilder();
        for(Double line: ans){
            str.append(line);
            str.append(" ");
        }
        writer.write(str.toString());
        writer.write("\n");
        writer.close();
    }
}
