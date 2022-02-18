package threads.synchronizers;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerExample {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                String valueFromMain = exchanger.exchange("From another thread");
                System.out.println("Value from main: " + valueFromMain);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        String value = "Main";
        String valueFromWorker = exchanger.exchange(value);
        System.out.println(valueFromWorker);
    }
}
