import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    String path;
    public Output(String path){
        this.path = path;
    }
    public void myPrint(ArrayList<Double> ans ) throws IOException {
        FileWriter writer = new FileWriter(this.path, true);
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
