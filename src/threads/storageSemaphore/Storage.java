package threads.storageSemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Storage {

    private final Semaphore semaphore = new Semaphore(Integer.MAX_VALUE,true);
    private volatile String value = "Default";

    String read() throws InterruptedException {
        semaphore.acquire();
        try {
            TimeUnit.SECONDS.sleep(1);
            return value;
        } finally {
            semaphore.release();

        }
    }

    void read(String newValue) throws InterruptedException {
        semaphore.acquire(Integer.MAX_VALUE);
        TimeUnit.SECONDS.sleep(1);
        value = newValue;
        semaphore.release(Integer.MAX_VALUE);
    }
}
