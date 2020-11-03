import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class Frame {
    Output output;
    private final JFrame jFrame = new JFrame("M/M/n模拟");
    private final Container container = jFrame.getContentPane();
    private int height = 50;
    private int line_dis = 5;
    private int textwidth = 300;
    private Font myFont = new Font("黑体", Font.PLAIN, 22);


    JLabel avgArriveTime = new JLabel("平均到达时间间隔 =");
    JTextField avgArriveTimeText = new JTextField();
    JLabel avgServTime = new JLabel("平均服务时间 =");
    JTextField avgServTimeText = new JTextField();
    JLabel maxQueueNum = new JLabel("队列中最大顾客数目 =");
    JTextField maxQueueNumText = new JTextField();
    JLabel serverNum = new JLabel("服务器数量 =");
    JTextField serverNumText = new JTextField();
    JLabel endTime = new JLabel("仿真最大时间 =");
    JTextField endTimeText = new JTextField();
    JButton okButton = new JButton("确定");

    JLabel clientNum = new JLabel("顾客数目");
    JTextField clientNumText = new JTextField();
    JLabel servedClientNum = new JLabel("服务顾客数");
    JTextField servedClientNumText = new JTextField();
    JLabel avgWaitNum = new JLabel("队列中平均等待客户数");
    JTextField avgWaitNumText = new JTextField();
    JLabel avgWaitTime = new JLabel("平均等待时间");
    JTextField avgWaitTimeText = new JTextField();
    JLabel avgStayTime = new JLabel("平均逗留时间");
    JTextField avgStayTimeText = new JTextField();
    JLabel avgActServTime = new JLabel("实际平均服务时间");
    JTextField avgActServTimeText = new JTextField();
    JLabel servUseRate = new JLabel("服务器利用率");
    JTextField servUseRateText = new JTextField();
    JLabel sumTime = new JLabel("总时间");
    JTextField sumTimeText = new JTextField();

    /*
        System.out.println("相关计算参数输出：");
        System.out.println("队列中平均等待客户数q = " + q/numChecks);//
        System.out.println("平均等待时间Tw = " + Tw/requests);//
        System.out.println("平均逗留时间Tq = " + Tq/served_count);
        System.out.println("实际平均服务时间Ts = " + sumServTime/served_count);
        System.out.println("服务器利用率 = " + sumServTime/endTime);//
        */

    public Frame(Output output){
        this.output = output;
        initialize();
    }

    public void initialize(){
        jFrame.setSize(1000, 1000);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        // 添加面板
        jFrame.add(panel);
        placeComponents(panel);

        // 设置界面可见
        jFrame.setVisible(true);
    }

    public void placeComponents(JPanel panel) {

        panel.setLayout(null);
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        int x = 10;
        int y = 20;


        avgArriveTime.setBounds(x,y,250,height);
        avgArriveTime.setFont(myFont);
        panel.add(avgArriveTime);
        avgArriveTimeText.setBounds(x + 300,y,textwidth,height);
        avgArriveTimeText.setFont(myFont);
        panel.add(avgArriveTimeText);


        y = y + height + line_dis;

        avgServTime.setBounds(x,y,250,height);
        avgServTime.setFont(myFont);
        panel.add(avgServTime);
        avgServTimeText.setBounds(x + 300,y,textwidth,height);
        avgServTimeText.setFont(myFont);
        panel.add(avgServTimeText);

        y = y + line_dis + height;


        maxQueueNum.setBounds(x,y,250,height);
        maxQueueNum.setFont(myFont);
        panel.add(maxQueueNum);
        maxQueueNumText.setBounds(x + 300,y,textwidth,height);
        maxQueueNumText.setFont(myFont);
        panel.add(maxQueueNumText);

        y = y + line_dis + height;

        serverNum.setBounds(x,y,250,height);
        serverNum.setFont(myFont);
        panel.add(serverNum);
        serverNumText.setBounds(x + 300,y,textwidth,height);
        serverNumText.setFont(myFont);
        panel.add(serverNumText);

        y = y + line_dis + height;

        endTime.setBounds(x,y,250,height);
        endTime.setFont(myFont);
        panel.add(endTime);
        endTimeText.setBounds(x + 300,y,textwidth,height);
        endTimeText.setFont(myFont);
        panel.add(endTimeText);

        y = y + line_dis + height;
        okButton.setBounds(x + 100, y, 150, height);
        okButton.setFont(myFont);
        panel.add(okButton);

        y = y + line_dis + height;
        JLabel ans = new JLabel("模拟结果");
        ans.setBounds(x + 100, y, 150, height);
        ans.setFont(myFont);
        panel.add(ans);

        //顾客数目
        y = y + line_dis + height;
        clientNum.setBounds(x,y,250,height);
        clientNum.setFont(myFont);
        panel.add(clientNum);
        clientNumText.setBounds(x + 300,y,textwidth,height);
        clientNumText.setFont(myFont);
        panel.add(clientNumText);

        //服务顾客数目
        y = y + line_dis + height;
        servedClientNum.setBounds(x,y,250,height);
        servedClientNum.setFont(myFont);
        panel.add(servedClientNum);
        servedClientNumText.setBounds(x + 300,y,textwidth,height);
        servedClientNumText.setFont(myFont);
        panel.add(servedClientNumText);


        //平均等待数目
        y = y + line_dis + height;
        avgWaitNum.setBounds(x,y,250,height);
        avgWaitNum.setFont(myFont);
        panel.add(avgWaitNum);
        avgWaitNumText.setBounds(x + 300,y,textwidth,height);
        avgWaitNumText.setFont(myFont);
        panel.add(avgWaitNumText);

        //平均等待时间
        y = y + line_dis + height;
        avgWaitTime.setBounds(x,y,250,height);
        avgWaitTime.setFont(myFont);
        panel.add(avgWaitTime);
        avgWaitTimeText.setBounds(x + 300,y,textwidth,height);
        avgWaitTimeText.setFont(myFont);
        panel.add(avgWaitTimeText);

        //平均逗留时间
        y = y + line_dis + height;
        avgStayTime.setBounds(x,y,250,height);
        avgStayTime.setFont(myFont);
        panel.add(avgStayTime);
        avgStayTimeText.setBounds(x + 300,y,textwidth,height);
        avgStayTimeText.setFont(myFont);
        panel.add(avgStayTimeText);

        //实际平均服务时间
        y = y + line_dis + height;
        avgActServTime.setBounds(x,y,250,height);
        avgActServTime.setFont(myFont);
        panel.add(avgActServTime);
        avgActServTimeText.setBounds(x + 300,y,textwidth,height);
        avgActServTimeText.setFont(myFont);
        panel.add(avgActServTimeText);

        //服务器利用率
        y = y + line_dis + height;
        servUseRate.setBounds(x,y,250,height);
        servUseRate.setFont(myFont);
        panel.add(servUseRate);
        servUseRateText.setBounds(x + 300,y,textwidth,height);
        servUseRateText.setFont(myFont);
        panel.add(servUseRateText);

        y = y + line_dis + height;
        sumTime.setBounds(x,y,250,height);
        sumTime.setFont(myFont);
        panel.add(sumTime);
        sumTimeText.setBounds(x + 300,y,textwidth,height);
        sumTimeText.setFont(myFont);
        panel.add(sumTimeText);

        addListener();

    }

    public void addListener(){
        okButton.addActionListener( new  ActionListener() {
            @Override
            public  void  actionPerformed(ActionEvent arg0) {
                double param1 = Double.parseDouble(avgArriveTimeText.getText());
                double param2 = Double.parseDouble(avgServTimeText.getText());
                int param3 = Integer.parseInt(maxQueueNumText.getText());
                int param4 = Integer.parseInt(serverNumText.getText());
                int param5 = Integer.parseInt(endTimeText.getText());
                Controller controller = new Controller(param1, param2, param3, param4, param5);

                ArrayList<Double>res = null;
                try {
                    res = controller.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayList<Double>myOutput = new ArrayList<>();
                myOutput.add(param1);
                myOutput.add(param2);
                myOutput.add(1.0 * param3);
                myOutput.add(1.0 * param4);
                clientNumText.setText(String.valueOf(res.get(0)));
                servedClientNumText.setText(String.valueOf(res.get(1)));
                avgWaitNumText.setText(String.valueOf(res.get(2)));
                avgWaitTimeText.setText(String.valueOf(res.get(3)));
                avgStayTimeText.setText(String.valueOf(res.get(4)));
                avgActServTimeText.setText(String.valueOf(res.get(5)));
                servUseRateText.setText(String.valueOf(res.get(6)));
                sumTimeText.setText(String.valueOf(res.get(7)));

                myOutput.addAll(res);
                try {
                    output.myPrint(myOutput);
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }
        });
    }

}
