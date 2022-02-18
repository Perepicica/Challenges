package threads.synchronizers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreExample {
    /**
     * Существует парковка, которая одновременно может вмещать не более 5 автомобилей.
     * Если парковка заполнена полностью,
     * то вновь прибывший автомобиль должен подождать пока не освободится хотя бы одно место.
     * После этого он сможет припарковаться.
     */
    public static void main(String[] args) {
        ExecutorService servise = Executors.newCachedThreadPool();
        Semaphore parking = new Semaphore(5);

        Runnable car = () -> {
            String name = Thread.currentThread().getName();
            try {
                parking.acquire();
                System.out.println("Заехал на парковку: " + name);
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Покидает парковку: " + name);
                parking.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        servise.submit(car);
        servise.submit(car);
        servise.submit(car);
        servise.submit(car);
        servise.submit(car);
        servise.submit(car);
        servise.submit(car);
        servise.shutdown();
    }
}
