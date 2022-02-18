package threads.synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierExample {
    /**
     * Существует паромная переправа.
     * Паром может переправлять одновременно по три автомобиля.
     * Чтобы не гонять паром лишний раз, нужно отправлять его,
     * когда у переправы соберется минимум три автомобиля.
     */
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, () -> {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println("Паром переправил автомобили!");
        } catch (InterruptedException e) {
        }
    });
    //Инициализируем барьер на три потока и таском, который будет выполняться, когда
    //у барьера соберется три потока. После этого, они будут освобождены.

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i)).start();
            TimeUnit.MILLISECONDS.sleep(400);
        }
    }

    //Стороны, которые будут достигать барьера
    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Автомобиль №%d подъехал к паромной переправе.\n", carNumber);
                //Для указания потоку о том что он достиг барьера, нужно вызвать метод await()
                //После этого данный поток блокируется, и ждет пока остальные стороны достигнут барьера
                BARRIER.await();
                System.out.printf("Автомобиль №%d продолжил движение.\n", carNumber);
            } catch (InterruptedException e) {
                System.out.println("Interrupted!");
            } catch (BrokenBarrierException ex) {
                System.out.println("Broken!");
            }
        }
    }
}
