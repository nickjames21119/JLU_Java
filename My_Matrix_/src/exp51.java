import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class exp51 extends Thread {
    final static int p = 9;
    final static int rA = 3;
    final static int cA = 3;
    final static int rB = 3;
    final static int cB = 3;
    static int testNum = p;
    static int totalActions = 0;
    public int actions = 0;
    static float[][] a;
    static float[][] b;
    static float[][] c;
    static Lock[][] locks;
    static Lock lock;
    static final Object waitAll = new Object();

    static {
        Scanner scanner = new Scanner(System.in);
        lock = new ReentrantLock();
        a = new float[rA][cA];
        b = new float[rB][cB];
        c = new float[rA][cB];
        locks = new ReentrantLock[rA][cB];
        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cB; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
//        for (int i = 0; i < cA; i++) {
//            a[0][i] = i;
//        }
//        for (int i = 0; i < rB; i++) {
//            b[i][0] = i;
//        }
        System.out.println("请输入矩阵A参数(注：" + rA + "行" + cA + "列)：");
        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cA; j++) {
                a[i][j] = scanner.nextInt();
            }
        }
        System.out.println("请输入矩阵B参数(注：" + rB + "行" + cB + "列)：");
        for (int i = 0; i < rB; i++) {
            for (int j = 0; j < cB; j++) {
                b[i][j] = scanner.nextInt();
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < rA; i++) {
            for (int j = 0; j < cB; j++) {
                lock.lock();
                if (locks[i][j].tryLock()) {
                    locks[i][j].lock();
                    totalActions++;
                    actions++;
                    lock.unlock();
                    for (int k = 0; k < cA; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                    try {
                        synchronized (waitAll) {
                            testNum--;
                            if (testNum == 0) {
                                testNum = p;
                                waitAll.notifyAll();
                            } else waitAll.wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    lock.unlock();
                }
            }
        }

    }

    public static void main(String[] args) {
        exp51[] tests = new exp51[p];
        for (int i = 0; i < p; i++) {
            tests[i] = new exp51();
        }
        for (int i = 0; i < p; i++) {
            tests[i].start();
        }
        try {
            for (int i = 0; i < p; i++) {
                tests[i].join();
            }
            System.out.println("完成的总任务数为：" + totalActions);
            for (int i = 0; i < p; i++) {
                System.out.println("线程" + i + "完成的任务数为：" + tests[i].actions);
            }
            System.out.println("答案为：");
            for (int i = 0; i < rA; i++) {
                for (int j = 0; j < cB; j++) {
                    System.out.printf("%10.2f", c[i][j]);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

