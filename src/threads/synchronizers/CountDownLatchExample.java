package threads.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class CountDownLatchExample {
    /**
     * Мы хотим провести автомобильную гонку.
     * В гонке принимают участие пять автомобилей.
     * Для начала гонки нужно, чтобы выполнились следующие условия:
     * Каждый из пяти автомобилей подъехал к стартовой прямой;
     * Была дана команда «На старт!»;
     * Была дана команда «Внимание!»;
     * Была дана команда «Марш!».
     * Важно, чтобы все автомобили стартовали одновременно.
     */

    private static CountDownLatch latch = new CountDownLatch(8);


    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Car(0, ThreadLocalRandom.current().nextInt(0, 100)));
        service.submit(new Car(1, ThreadLocalRandom.current().nextInt(0, 100)));
        service.submit(new Car(2, ThreadLocalRandom.current().nextInt(0, 100)));
        service.submit(new Car(3, ThreadLocalRandom.current().nextInt(0, 100)));
        service.submit(new Car(4, ThreadLocalRandom.current().nextInt(0, 100)));
        service.shutdown();

        while (latch.getCount() != 3) ;
        System.out.println("На старт!");
        latch.countDown();
        System.out.println("Внимание!");
        latch.countDown();
        System.out.println("Марш!");
        latch.countDown();

    }

    private static class Car implements Runnable {
        int number;
        int speed;

        public Car(int number, int speed) {
            this.number = number;
            this.speed = speed;
        }

        @Override
        public void run() {
            try {
                latch.countDown();
                latch.await();
                System.out.println(number + " started!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
