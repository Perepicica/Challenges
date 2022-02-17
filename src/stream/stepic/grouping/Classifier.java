package stream.stepic.grouping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Classifier {

    /*
     Need to implement a function that accepts a list of words and groups them by their length
     */
    public static Map<Integer, List<String>> group(List<String> words) {
        return words.stream().collect(Collectors.groupingBy(String::length));
    }
}