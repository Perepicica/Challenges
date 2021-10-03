package stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class StreamTasks {
    /**
     * на вход функция получает Stream организаций;
     * каждая организация содержит список с рабочими;
     * необходимо вернуть Stream c уникальными именами сотрудников всех организаций, где кол-во сотрудников ненулевое;
     * можно пользоваться следующими методами Stream API: filter, map и flatMap, distinct.
     *
     * @param organization
     */
    static List<String> uniqueName(List<Organization> organization) {
        return organization.stream()
                .filter(org -> org.getWorkers().size() > 0)
                .flatMap(org -> org.getWorkers().stream())
                .map(Worker::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Нужно написать коллектор, который создаст Map, где ключом будет id организации, а в качестве значения — сама организация;
     * Гарантируется, что id у организации будет уникальным.
     *
     * @param organizations
     */
    static Map<Integer, Organization> myMapCollector(List<Organization> organizations) {
        return organizations.stream()
                .collect(Collectors.toMap(Organization::getId, o -> o));
    }

    /**
     * Нужно упорядочить сотрудников по количеству очков и выбрать трёх лучших из них.
     * Нужно написать коллектор, который создаст строку с именами трех лучших сотрудников, разделенными запятой и пробелом.
     * Имена сотрудников в строке должны идти в порядке убывания количества очков.
     * Гарантируется, что количество очков у всех сотрудников разное.
     *
     * @param workers
     */
    static String bestWorkers(List<Worker> workers) {
        return workers.stream()
                .sorted(Comparator.comparing(WorkerInterface::getPoints).reversed())
                .limit(3).map(WorkerInterface::getName)
                .collect(Collectors.joining(", "));
    }

    /**
     * Нужно написать коллектор, который создаст Map, где ключом будет должность,
     * а в качестве значения будет список рабочих, которые занимают эту должность.
     *
     * @param workers
     */
    void groupingWorkers(List<WorkerInterface> workers) {
        Map<String, List<WorkerInterface>> actual = workers.stream()
                .collect(Collectors.groupingBy(WorkerInterface::getPosition));
    }

    /**
     * Нужно написать коллектор, который создаст Map,
     * где ключом будет должность, а значения - количество рабочих,
     * которые занимают эту должность.
     *
     * @param workers
     */
    void workersOnPositionCounter(List<WorkerInterface> workers) {
        Map<String, Long> actual = workers.stream()
                .collect(Collectors.groupingBy(WorkerInterface::getPosition, Collectors.counting()));
    }

    /**
     * Нужно написать коллектор, который создаст Map, где ключ - должность,
     * а в качестве значения - средняя зарплата для этой должности.
     *
     * @param workers
     */
    void averageSalaryOnPosition(List<WorkerInterface> workers) {
        Map<String, Double> actual = workers.stream()
                .collect(
                        Collectors.groupingBy(WorkerInterface::getPosition, Collectors.averagingDouble(WorkerInterface::getSalary))
                        );
    }
}
