package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organization implements OrganizationInterface {
    private int id;
    private List <Worker> workers = new ArrayList<>();

    public Organization(int id, List<Worker> workers) {
        this.id = id;
        this.workers = workers;
    }

    @Override
    public List<Worker> getWorkers() {
        return workers;
    }

    public void addWorkers(List<Worker> workers){
        this.workers.addAll(workers);
    }

    @Override
    public int getId() {
        return id;
    }
}
