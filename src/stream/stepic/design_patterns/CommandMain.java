package stream.stepic.design_patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Command pattern explanation and example
 */
interface Command {
    void execute();
}

class SpeakerImp implements Speaker {

    @Override
    public void volumeUp() {
        System.out.println("volume up");
    }

    @Override
    public void volumeDown() {
        System.out.println("volume down");
    }

    @Override
    public void play() {
        System.out.println("play");
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }
}

interface Speaker {

    void volumeUp();

    void volumeDown();

    void play();

    void pause();
}

class VolumeUp implements Command {

    private final Speaker speaker;

    public VolumeUp(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void execute() {
        speaker.volumeUp();
    }
}

class VolumeDown implements Command {

    private final Speaker speaker;

    public VolumeDown(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void execute() {
        speaker.volumeDown();
    }
}

class Play implements Command {

    private final Speaker speaker;

    public Play(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void execute() {
        speaker.play();
    }
}

class Pause implements Command {

    private final Speaker speaker;

    public Pause(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void execute() {
        speaker.pause();
    }
}

class App {
    private final List<Command> commands;

    public App() {
        commands = new ArrayList<>();
    }

    public void add(Command command) {
        commands.add(command);
    }

    public void run() {
        for (Command command : commands) {
            command.execute();
        }
    }
}

class CommandMain {

    public static void main(String[] args) {
        Speaker speaker = new SpeakerImp();

        App app = new App();

        Command pause = speaker::pause;
        app.add(pause);
        app.add(new VolumeUp(speaker));
        app.add(new VolumeUp(speaker));
        app.add(new VolumeDown(speaker));
        app.add(new VolumeDown(speaker));
        app.add(new Pause(speaker));

        app.run();
    }
}