import java.io.FileWriter;
import java.io.IOException;

public class OutputNew {
    String path;
    public OutputNew(String path){
        this.path = path;
    }
    public void myPrint(double time, int sum, int served, int waiting) throws IOException {
        FileWriter writer = new FileWriter(this.path, true);
        StringBuilder str = new StringBuilder();
        str.append(time);
        str.append(" ");
        str.append(sum);
        str.append(" ");
        str.append(served);
        str.append(" ");
        str.append(waiting);
        writer.write(str.toString());
        writer.write("\n");
        writer.close();
    }
}
