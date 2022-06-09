
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/*
CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）。
CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。
计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成一些任务，
然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。


CountDownLatch典型用法：1、某一线程在开始运行前等待n个线程执行完毕。将CountDownLatch的计数器初始化为new CountDownLatch(n)，
每当一个任务线程执行完毕，就将计数器减1 countdownLatch.countDown()，当计数器的值变为0时，在CountDownLatch上await()的线程就会被唤醒。
一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。
CountDownLatch典型用法：2、实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。
类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。做法是初始化一个共享的CountDownLatch(1)，将其计算器初始化为1，
多个线程在开始执行任务前首先countdownlatch.await()，当主线程调用countDown()时，计数器变为0，多个线程同时被唤醒。

 */


public class Matrix {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        float[][] A = readMatrix();                                          //读取矩阵，自定义函数
        float[][] B = readMatrix();                                          //读取矩阵，自定义函数

        int num_thread = A.length;

        float[][] parallel_result = new float[A.length][B[0].length];          //结果矩阵

        // 子线程的分片计算间隔
        int gap = A.length / num_thread;                                    //A.length获取矩阵行数

        CountDownLatch latch = new CountDownLatch(num_thread);              //同步工具类，协调线程之间同步(防止计算完成前打印结果矩阵)

        TestThread[] threads = new TestThread[num_thread];                  //创建线程数组实例

        for (int i = 0; i < num_thread; i++)                                //初始化各个线程
            threads[i] = new TestThread(A, B, i, gap, parallel_result, latch);

        // 启动线程
        for (int i = 0; i < num_thread; i++)                               //循环依次启动各个线程
            threads[i].start();                                           //线程启动后可自动调用run方法

        latch.await();                                                    //所有子线程执行完之后才能执行下一段代码

        printMatrix(parallel_result);
    }

    static class TestThread extends Thread {
        private float[][] A;
        private float[][] B;
        private int index;
        private int gap;
        private float[][] result;
        private CountDownLatch latch;

        public TestThread(float[][] A, float[][] B, int index, int gap, float[][] result, CountDownLatch latch) {
            this.latch = latch;
            this.A = A;
            this.B = B;
            this.index = index;
            this.gap = gap;
            this.result = result;
            this.latch = latch;
        }


        public void run() {                                             // 计算特定范围内的结果，重写thread中run方法
            System.out.println("Thread"+index+"start!");
            for (int i = index * gap; i < (index + 1) * gap; i++)
                for (int j = 0; j < B[0].length; j++)
                    for (int k = 0; k < B.length; k++)
                        result[i][j] += A[i][k] * B[k][j];

            latch.countDown();                                          //每结束一个线程latch中的计数器-1
        }
    }

    public static float[][] readMatrix() {          //自定义读取矩阵函数
        int rows = sc.nextInt();                  //输入矩阵行
        int cols = sc.nextInt();                  //输入矩阵列
        float[][] result = new float[rows][cols];     //返回读入矩阵

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = sc.nextInt();      //依次读入矩阵元素

        return result;
    }

    public static void printMatrix(float[][] mat) {
        System.out.println("矩阵[" + mat.length + "][" + mat[0].length + "]");         //提示打印矩阵维度
        int rows = mat.length;                                                        //行数
        int columns = mat[0].length;                                                  //列数

        for (float[] ints : mat) {                                                      //依次打印矩阵元素，所占四个位置(冒号函数，迭代思想)
            for (float j : ints)
                System.out.printf("%4f ", j);
            System.out.println();
        }
    }
}