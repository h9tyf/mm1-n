import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        //Controller c1 = new Controller(0.015, 0.015,1000, 1, 1000);
        //c1.run();
        //System.out.println("仿真结束");

        double avgArriveTime = 0.2;
        double avgServTime = 1;
        int max_queue_num = 1000;
        int server_num;
        double endTime = 100;

        //Output o1 = new Output("./src/output1.txt");
        //Output o2 = new Output("./src/output2.txt");
        //Output o3 = new Output("./src/output3.txt");
        Output o4 = new Output("./src/output_for_n.txt");

        //mm1
        //只改变avgServTime
        //server_num = 1;
        //avgServTime = 0.3;
        //Controller controller = new Controller(avgArriveTime, avgServTime, max_queue_num, server_num, endTime);
        //controller.run();

        //对比分析output2
/*
        for(server_num = 1; server_num <= 10; server_num++){
            Controller controller = new Controller(avgArriveTime, avgServTime, max_queue_num, server_num, endTime);
            ArrayList<Double> forPrint = new ArrayList();
            forPrint.add(avgArriveTime);
            forPrint.add(avgServTime);
            forPrint.add(1.0 * max_queue_num);
            forPrint.add(1.0 * server_num);
            forPrint.add(endTime);
            forPrint.addAll(controller.run());
            o4.myPrint(forPrint);
        }
*/
        //举例分析
        //取svgSErvTime = 0.1, 0.2, 0.3对比
        //画出随时间变化图像,
        //总人数随时间变化，
        //排队人数随时间变化
        //已经服务人数随时间变化
        //

        //mmn
        //改变server_num, 对每个servernum, 改变svgServTime

        Output output = new Output("./src/output_no_use.txt");
        Frame myFrame = new Frame(output);
    }
}
