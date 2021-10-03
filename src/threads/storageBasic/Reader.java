package threads.storageBasic;

//писатель только один
//идет в хранилище раз в 3 сек
//когда он в хранилище - никого из читателей быть не должно там
//у него приоритет перед читателями

import java.util.concurrent.TimeUnit;

public class Reader extends Thread {

    private final Storage storage;

    public Reader(String name, Storage storage) {
        super(name);
        this.storage = storage;
    }

    @Override

    public void run() {
        for (int i = 0; i < 30; i++) {
            String actualValue = storage.read();
            System.out.println(getName()+"got value: "+actualValue);
            try {
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
