package threads.storageLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//use: Lock
class Storage {

    private final Lock readLock;
    private final Lock writeLock;
    private volatile String value = "Default";

    Storage() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    String read() {
        readLock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }

        return value;
    }

    void read(String newValue) {
        writeLock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            value = newValue;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
