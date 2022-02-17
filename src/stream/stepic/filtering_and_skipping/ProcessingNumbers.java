package stream.stepic.filtering_and_skipping;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ProcessingNumbers {

    /**
     * implement the getStream method that produces a stream containing sorted unique numbers
     * that are strictly less than 100.
     */
    public static Stream<Integer> getStream(Collection<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .distinct()
                .takeWhile(n -> n < 100);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Collection<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(HashSet::new));

        String result = getStream(numbers)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}