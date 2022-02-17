package stream;

public class Worker implements WorkerInterface {
    private final String name;
    private final double salary;
    private final String position;
    private final int points;

    public Worker(String name, double salary, String position, int points) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.points = points;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public double getSalary() {
        return salary;
    }
}
