package threads.customArrayBlockingQueue;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<E> {
    private final Object data[];
    private final Lock lock;
    private int putIndex;
    private int takeIndex;
    private int count;
    private final Condition notEmpty;
    private final Condition notFull;

    public ArrayBlockingQueue(int size) {
        this(size, false);
    }

    public ArrayBlockingQueue(int size, boolean fair) {
        this.data = new Object[size];
        this.lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public boolean offer(E element) {
        Objects.requireNonNull(element);
        lock.lock();
        try {
            if (count == data.length) {
                return false;
            } else {
                enqueue(element);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean add(E element) throws IllegalStateException {
        if (offer(element)) {
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    public void put(E element) throws InterruptedException {
        Objects.requireNonNull(element);
        lock.lockInterruptibly();
        try {
            while (count == data.length) {
                notFull.await();
            }
            enqueue(element);
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(E element, long timeout, TimeUnit timeUnit) throws InterruptedException {
        Objects.requireNonNull(element);
        lock.lockInterruptibly();
        long nanos = timeUnit.toNanos(timeout);
        try {
            while (count == data.length) {
                if (nanos <= 0) return false;
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(element);
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E element) {
        data[putIndex] = element;
        putIndex = (putIndex + 1) % data.length;
        if (++count == 1) {
            notEmpty.signal();
        }
    }

    public E peek() {
        lock.lock();
        try {
            return takeLast();
        } finally {
            lock.unlock();
        }
    }

    public E element() {
        E peek = peek();
        if (peek != null) {
            return peek;
        }
        throw new NoSuchElementException();
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E poll() {
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit timeUnit) throws InterruptedException {
        lock.lockInterruptibly();
        long nanos = timeUnit.toNanos(timeout);
        try {
            while (count == 0) {
                if (nanos <= 0) return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(Object object) {
        Objects.requireNonNull(object);
        lock.lock();
        try {
            if (count > 0) {
                for (int i = takeIndex, end = putIndex, to = (i < end) ? end : data.length; ; i = 0, to = end) {
                    for (; i < to; ++i) {
                        if (object.equals(data[i])) {
                            removeAt(i);
                            return true;
                        }
                    }
                    if (to == end) {
                        break;
                    }
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void removeAt(int removeIndex) {
        if (removeIndex == takeIndex) {
            dequeue();
        } else {
            for (int i = removeIndex; ; ) {
                int pred = i;
                i = (i + 1) % data.length;
                if (i == putIndex) {
                    data[pred] = null;
                    putIndex = pred;
                    break;
                }
                data[pred] = data[i];
            }
            --count;
            notFull.signal();
        }
    }

    private E takeLast() {
        return (E) data[takeIndex];
    }

    private E dequeue() {
        E element = takeLast();
        data[takeIndex] = null;
        takeIndex = (takeIndex++) % data.length;
        count--;
        if (count == data.length - 1) {
            notFull.signal();
        }
        return element;
    }
}
