import sun.security.krb5.internal.Ticket;
/**
 * 注意事项：
 * 1.sleep时间不能过长也不能短，过长可能只会使用第五个窗口，过短可能只会使用第一个窗口
 * 2.synchronized(this)，给线程上锁，防止num被减多次，造成出票为负的情况
 */
import java.util.Random;

public class Main implements Runnable {
    static int num = 101;       //火车票数

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //同步代码块
        while(num > 0){
            synchronized(this){                //同步锁
                num--;
            }
            if(num >= 1){                      //双重校验
                System.out.println("窗口"+Thread.currentThread().getName()+"出售车票编号"+num+"成功~");  //打印窗口+车票
                /*try {
                    Thread.sleep(new Random().nextInt(2000));   //睡眠时间函数
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/


            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main ticket = new Main();                        //实例化对象
        for(int line = 1;line <= 5;line++){              //五个窗口，即五个线程
            new Thread(ticket,""+line).start();    //宏观上五个线程平行作业
        }
    }
}
