package 线程的字符串处理问题;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main2 {

    public volatile static int count = 0;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //Starts 10 threads
        for (int i = 0; i < 10; i++) {
            pool.submit(new calThread());
        }

        pool.shutdown();

        while (true) {
            if (pool.isTerminated()) {
                System.out.println("count=" + count);
                System.exit(0);
            }
        }
    }
}

class calThread implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Increases by 100 per thread
        for (int i = 0; i < 100; i++) {
            Main2.count++;
        }
    }

}