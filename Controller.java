import java.io.IOException;
import java.util.*;
import java.util.Random;

public class Controller {

    public static final int BIRTH = 0;
    public static final int DEATH = 1;
    //input
    double avgArriveTime;			// 平均到达时间
    double avgServTime;				// 平均服务时间
    int max_queue_num;              //队列最多顾客数
    int server_num;                 //服务器数量

    //temp

    LinkedList<Event> schedule;     // 事件队列
    double clock;					// 时钟
    double nextArrival;				// 服务开始时间
    double nextDeparture;			// 服务结束时间
    ArrayList<Double> nextDepartureList;
    ArrayList<Double> checkpoints;	// 监控测点
    int numChecks;					// 监控测点数

    OutputNew outputNew;


    double Tq,Tw;
    int w, q;
    //output
    double sumServTime;
    double endTime;
    int served_count;
    int requests;


    /* 初始化 */
    public Controller(double avgArriveTime, double avgServTime, int max_queue_num, int server_num, double endTime) {
        outputNew = new OutputNew("./src/output_222_3.txt");
        this.avgArriveTime = avgArriveTime;
        this.avgServTime = avgServTime;
        this.max_queue_num = max_queue_num;
        this.server_num = server_num;

        this.served_count = 0;
        this.endTime = endTime;
        this.requests = 0;

        checkpoints = new ArrayList<Double>();
        double n = 0;
        while (n < endTime) {
            numChecks++;
            n += exponential(this.avgArriveTime);
            checkpoints.add(n);
        }

        for(Double d: checkpoints){
            System.out.println(d);
        }


        clock = 0;
        schedule = new LinkedList<Event>();
        nextArrival = exponential(1/this.avgArriveTime);
        nextDeparture = Double.POSITIVE_INFINITY;//无限大
        nextDepartureList = new ArrayList<>();

        Tq = 0;
        Tw = 0;
        sumServTime = 0;
        w= 0;
        q = 0;
    }

    public void printChange() throws IOException {
        outputNew.myPrint(clock,requests, served_count, getWaitNum());
    }

    /* 下一个顾客到达 */
    public void birthHandler(double time) {
        //if (schedule.isEmpty()) { // 队列为空，执行服务
        if(getServingNum() < server_num){
            scheduleDeath(time);
        }
        else { // 队列不为空，添加此顾客到队列中
            schedule.add(new Event(time,BIRTH));
        }
        nextArrival += exponential(1/this.avgArriveTime);
    }

    public int getWaitNum(){
        int count = 0;
        for(Event e: schedule){
            if(e.getType() == BIRTH){
                count++;
            }
        }
        return count;
    }

    public int getServingNum(){
        int count = 0;
        for(Event e: schedule){
            if(e.getType() == DEATH){
                count++;
            }
        }
        return count;
    }

    /* 当前服务结束 */
    public void deathHandler() {
        removeClient();
        findNewNextDe();
        //schedule.remove(); // 事件移除
        if (!schedule.isEmpty()) { // 队列不为空：弹出执行队列中的任务
            //Event next = schedule.remove();
            Event next = getNextClient();
            if(next == null){
                findNewNextDe();
            }else {
                scheduleDeath(next.getTime());
            }
        } else{
            // 队列为空，此时服务器空闲，等待下一任务
            nextDeparture = Double.POSITIVE_INFINITY;
            nextArrival += exponential(1/this.avgArriveTime);
        }
    }

    public void findNewNextDe(){
        nextDeparture = Double.POSITIVE_INFINITY;
        for(Event e:schedule){
            if(e.getType() == DEATH && e.getTime() < nextDeparture){
                nextDeparture = e.getTime();
            }
        }
    }

    public void printSch(){
        System.out.println("@@@@@@@");
        for (Event event: schedule) {
            System.out.println(event.getType());
            System.out.println(event.getTime());
        }
        System.out.println("########################");
    }
    public void removeClient(){
        for(Double num:nextDepartureList){
            if(num == nextDeparture){
                nextDepartureList.remove(num);
                break;
            }
        }
        for (Event event: schedule) {
            if (event.getType() == DEATH && event.getTime() == nextDeparture) {
                schedule.remove(event);
                //printSch();
                break;
            }
        }
    }

    public Event getNextClient(){
        Event e = null;
        for(Event event:schedule){
            if(event.getType() == BIRTH){
                e = event;
                schedule.remove(event);
                break;
            }
        }
        return e;
    }

    /* 服务执行，计算相关参数 */
    public void scheduleDeath(double arrivalTime) {
        double temp = clock + exponential(1/this.avgServTime);  // 本次服务结束时间
        nextDepartureList.add(temp);
        schedule.addFirst(new Event(temp,DEATH));
        findNewNextDe();
        this.served_count++;  // 总服务数
        Tq += (temp - arrivalTime); // 总逗留时间
        Tw += (clock - arrivalTime);  // 总等待时间
        sumServTime += (temp - clock);  // 总服务时间
    }

    /* 监控输出 */
    public void monitorHandler() {
        int cur_q = schedule.size();
        int cur_w = (cur_q > 0) ? (schedule.size() - 1) : 0;
        w += cur_w;
        q += cur_q;
        checkpoints.remove(0);
    }

    /* 指数分布生成函数 */
    public static double exponential(double lambda) {
        Random r = new Random();
        double x = Math.log(1-r.nextDouble())/(-lambda);
        return x;
    }

    /* 主函数 */
    public ArrayList<Double> run() throws IOException {
        while (clock < endTime) {
            if (checkpoints.get(0) < nextArrival && checkpoints.get(0) < nextDeparture) {
                // 监控时刻位于服务发生时
                clock = checkpoints.get(0);
                monitorHandler();
            }
            else if (nextArrival <= nextDeparture) { // 下一个顾客的到来
                if(getWaitNum() >= max_queue_num){
                    endTime = clock;
                    System.out.println("full");
                    monitorHandler();
                    break;
                }
                clock = nextArrival;
                birthHandler(nextArrival);
                requests++;
            }
            else {	// 服务结束
                clock = nextDeparture;
                deathHandler();
            }
            printChange();
        }
        return printStats();
    }

    public ArrayList<Double> printStats() {
        // 输出相关参数

        System.out.println("相关计算参数输出：");
        System.out.println("顾客数目requests: " + requests);
        System.out.println("服务顾客数serviced: " + served_count);
        System.out.println("队列中平均等待客户数q = " + q/numChecks);
        System.out.println("平均等待时间Tw = " + Tw/requests);
        System.out.println("平均逗留时间Tq = " + Tq/served_count);
        System.out.println("实际平均服务时间Ts = " + sumServTime/served_count);
        System.out.println("服务器利用率 = " + (sumServTime/clock)/server_num);
        System.out.println("总时间 = " + clock);

        ArrayList<Double> res = new ArrayList<>();
        res.add(1.0 * requests);
        res.add(1.0 * served_count);
        res.add(((double)q)/((double)numChecks));
        res.add(Tw/requests);
        res.add(Tq/served_count);
        res.add(sumServTime/served_count);
        res.add((sumServTime/clock)/server_num);
        res.add(clock);
        return res;
    }
}