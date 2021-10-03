package threads.storageBasic;

import java.util.concurrent.TimeUnit;

//use: synchronized, wait, notify, volatile
class Storage {

    private final Object readLock = new Object();
    private final Object writeLock = new Object();
    private volatile String value = "Default";
    private volatile int reading = 0;

    String read() {
        synchronized (writeLock) { //проверяет есть ли ожидающий где-то писатель
            ++reading;             //если да - лочится, если нет заходит читать
        }
        try {
            TimeUnit.SECONDS.sleep(1);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            synchronized (readLock) { //проверяем второй лок
                if(--reading==0){       //если все чиатели вышли - будим писателя
                    readLock.notify();
                }
            }

        }

        return value;
    }

    void read(String newValue) {
        synchronized (writeLock) {   //обозначаем, что пришёл писатель
            synchronized (readLock) { //подписываемся на уведомление, что все вышли
                while (reading > 0) {
                    try {
                        readLock.wait();  //ждёмс
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
                value = newValue;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//только когда обновили значение, освобождаем монитор
    }
}
