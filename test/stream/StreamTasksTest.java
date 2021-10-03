package stream;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StreamTasksTest {
    private Worker Alex = new Worker("Alex", 500, "Manager", 22);
    private Worker Mike = new Worker("Mike", 500, "Manager", 21);
    private Worker John = new Worker("John", 500, "Manager", 20);
    private Worker Nico = new Worker("Niko", 500, "Manager", 12);
    private Organization org1 = new Organization(1, Arrays.asList(Alex, John));
    private Organization org2 = new Organization(2, Arrays.asList(Alex, Mike));

    @Test
    void uniqueNameTest() {
        assertEquals(Arrays.asList("Alex", "John", "Mike"),
                StreamTasks.uniqueName(Arrays.asList(org1, org2)));
    }

    @Test
    void myMapCollectorTest() {
    Map<Integer,Organization> myMap = new HashMap<>();
    myMap.put(1, org1);
    myMap.put(2, org2);

    assertEquals(myMap,
            StreamTasks.myMapCollector(Arrays.asList(org1,org2)));
    }

    @Test
    void bestWorkersTest() {
        assertEquals("Alex, Mike, John",
                StreamTasks.bestWorkers(Arrays.asList(Mike,Nico,Alex,John)));
    }
}