package stream.stepic.grouping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Partitioner {

    /**
     * Complete the getPartition method that produces two partitions:
     * free applications and paid ones.
     * The partitions should be organized into a Map that contains free application by the true key
     * and paid ones by the false key.
     */
    public static Map<Boolean, List<Application>> getPartition(List<Application> applications) {
        return applications.stream().collect(Collectors.partitioningBy(Application::isFree));
    }
}

class Application {
    private final String name;
    private final boolean isFree;

    public Application(String name, boolean isFree) {
        this.name = name;
        this.isFree = isFree;
    }

    public String getName() {
        return name;
    }

    public boolean isFree() {
        return isFree;
    }
}