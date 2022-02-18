package threads.customBlockingStack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingStack<E> {
    private final Object[] data;
    private int count;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public BlockingStack(int size) {
        this(size, false);
    }

    public BlockingStack(int size, boolean fair) {
        data = new Object[size];
        lock = new ReentrantLock(fair);
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();

    }

    public void push(E element) throws InterruptedException {
        lock.lock();
        try {
            while (count == data.length) {
                notFull.await();
            }
            data[count] = element;
            count++;
            if (count == 1) {
                notEmpty.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public E pop() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            E el = (E) data[count - 1];
            count--;
            data[count - 1] = null;
            if (count == data.length - 1) {
                notFull.signal();
            }
            return el;
        } finally {
            lock.unlock();
        }
    }

    public E peek() {
        lock.lock();
        try {
            return (E) data[count - 1];
        } finally {
            lock.unlock();
        }
    }
}
