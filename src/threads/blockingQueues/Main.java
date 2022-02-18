package threads.blockingQueues;

import java.util.concurrent.*;

public class Main {
    static class Box {
        String destination;
        public Box(String destination) {
            this.destination = destination;
        }
    }

    public static void main(String[] args) {
        int capacity = 10;
        BlockingQueue<Box> desk = new ArrayBlockingQueue<>(capacity);
        Runnable operator = () -> {
            try {
                for (int i = 0; i < capacity; i++) {
                    System.out.println("Prepare the box number " + i);
                    TimeUnit.SECONDS.sleep(1);
                    Box box = new Box(String.valueOf(i));
                    System.out.println("Trying to put " + i + " on the desk");
                    desk.put(box);
                    System.out.println("Successfully put" + i + " on the desk");

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Runnable courier = () -> {
            try {
                for (int i = 0; i < capacity; i++) {
                    System.err.println("Trying to take box from the desk");
                    desk.take();
                    System.err.println("Took the box " + i + ", goung to deliver");
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(operator);
        service.submit(courier);
        service.shutdown();
    }
}
