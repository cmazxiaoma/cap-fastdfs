package 线程的字符串处理问题;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main1 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(new TestThread1(lock)).start();
    }
}

class TestThread1 implements Runnable {
    private final Lock lock;

    public TestThread1(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        // throw IllegalMonitorStateException
        method0();
        // method1();
        // method2();
    }

    private void method0() {
        lock.lock();

        try {
            this.notifyAll();
        } finally {
            lock.unlock();
        }
    }

    private synchronized void method1() {
        notifyAll();
    }

    private void method2() {
        lock.lock();

        try {
            Condition condition = lock.newCondition();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}
