package stream.stepic.filtering_and_skipping;

import java.util.Arrays;
import java.util.List;

class SearchJavaEvents {

    /**
     * Here you need to implement the printSortedJavaEvents method
     * that takes a list of event titles and must print only the names of Java-related events
     * in the alphabetical order.
     * <p>
     * An event is considered a Java-related if it contains the substring "Java" in the title.
     * It is important that this substring can be written in any case, and they all must count.
     */
    public static void printSortedJavaEvents(List<String> events) {
        events.stream()
                .filter(event -> event.toLowerCase().contains("java"))
                .sorted()
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<String> events = Arrays.asList(
                "JavaZone",
                "KotlinConf",
                "java night party | Amsterdam",
                "Day of JAVA",
                "PyCon US");
        printSortedJavaEvents(events);
    }
}