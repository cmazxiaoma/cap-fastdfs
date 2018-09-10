package 线程的字符串处理问题;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        MainLock lock = new MainLock(30);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(new AThread(lock));
        pool.submit(new BThread(lock));
        pool.submit(new CThread(lock));

        pool.shutdown();
    }
}

class AThread implements Runnable {
    private final MainLock lock;

    public AThread(MainLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (lock.isA()) {
                    if (lock.isEnd()) {
                        System.exit(1);
                    } else {
                        print();
                    }
                    lock.increase();
                    lock.getB().signal();
                } else {
                    try {
                        lock.getA().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void print() {
        System.out.println("A ");
    }
}

class BThread implements Runnable {
    private final MainLock lock;

    public BThread(MainLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (lock.isB()) {
                    if (lock.isEnd()) {
                        System.exit(1);
                    } else {
                        print();
                    }
                    lock.increase();
                    lock.getC().signal();
                } else {
                    try {
                        lock.getB().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void print() {
        System.out.println("B ");
    }
}

class CThread implements Runnable {
    private final MainLock lock;

    public CThread(MainLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (lock.isC()) {
                    if (lock.isEnd()) {
                        System.exit(1);
                    } else {
                        print();
                    }
                    lock.increase();
                    lock.getA().signal();
                } else {
                    try {
                        lock.getC().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private void print() {
        System.out.println("C ");
    }
}