package threads.synchronizers;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        System.out.println(phaser.getPhase());
        System.out.println(phaser.getRegisteredParties());
        System.out.println(phaser.getArrivedParties());

        phaser.arrive();
        System.out.println(phaser.getPhase());
        phaser.register();
        System.out.println(phaser.getRegisteredParties());
    }
}
