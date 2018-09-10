package 线程的字符串处理问题;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main3 {

    public static void main(String args[]) {
        MainLock lock = new MainLock(9);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(new XThread(lock));
        pool.submit(new YThread(lock));
        pool.submit(new ZThread(lock));

        pool.shutdown();
    }
}

class XThread implements Runnable {
    private final MainLock lock;

    public XThread(MainLock lock) {
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
        System.out.print("abc".charAt(lock.getCount() / 3));
    }

}

class YThread implements Runnable {
    private final MainLock lock;

    public YThread(MainLock lock) {
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
        System.out.print("def".charAt(lock.getCount() / 3));
    }

}

class ZThread implements Runnable {
    private final MainLock lock;

    public ZThread(MainLock lock) {
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
        System.out.print("ghi".charAt(lock.getCount() / 3));
    }

}
