import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class exp5 extends Thread {
    static float[][] a;
    static float[][] b;
    static float[][] c;
    static Lock[][] locks;
    static Lock lock;
    final static int rA = 3;
    final static int cA = 3;
    final static int rB = 3;
    final static int cB = 3;
    public int actions = 0;

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
                    lock.unlock();
                    try {
                        sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    actions++;
                    for (int k = 0; k < cA; k++) {
                        c[i][j] += a[i][k] * b[k][j];
                    }
                    locks[i][j].unlock();
                } else {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        exp51 test1 = new exp51();
        exp51 test2 = new exp51();
        exp51 test3 = new exp51();
        exp51 test4 = new exp51();
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        try {
            test1.join();
            test2.join();
            test3.join();
            test4.join();
            System.out.println(test1.actions);
            System.out.println(test2.actions);
            System.out.println(test3.actions);
            System.out.println(test4.actions);
            for (int i = 0; i < rA; i++) {
                for (int j = 0; j < cB; j++) {
                    System.out.printf("%5.2f", c[i][j]);
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}