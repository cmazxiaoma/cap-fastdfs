package 线程的字符串处理问题;



import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MainLock extends ReentrantLock {

    private static final long serialVersionUID = 7103258623232795241L;

    private int count = 0;

    private final int max;

    private final Condition a;

    private final Condition b;

    private final Condition c;

    public MainLock(int max) {
        this.max = max;
        this.a = this.newCondition();
        this.b = this.newCondition();
        this.c = this.newCondition();
    }

    public boolean isEnd() {
        if (count >= max) {
            return true;
        }

        return false;
    }

    public void increase() {
        count++;
    }

    public int getCount() {
        return this.count;
    }

    public int getMax() {
        return this.max;
    }

    public Condition getA() {
        return this.a;
    }

    public Condition getB() {
        return this.b;
    }

    public Condition getC() {
        return this.c;
    }

    public boolean isA() {
        return count % 3 == 0;
    }

    public boolean isB() {
        return count % 3 == 1;
    }

    public boolean isC() {
        return count % 3 == 2;
    }
}
