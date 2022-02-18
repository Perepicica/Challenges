package threads.storageLock;

//может быть много читателей
//одновременно все могут считывать значение
//обращаются в storageBasic раз в секунду
//чтение длится 0,5 сек

import java.util.concurrent.TimeUnit;

public class Writer extends Thread {
    private final Storage storage;

    public Writer(Storage storage) {
        super("Writer");
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            storage.read(String.valueOf(i));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
