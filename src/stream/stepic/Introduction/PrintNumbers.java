package stream.stepic.Introduction;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PrintNumbers {
    /*
    There is a method named printNumbers that accepts a stream of numbers.
    Implement the method so that it prints out n numbers starting from the k-th number.
    We suppose that the counting starts from 1 and each number must be printed on a new line.
     */
    public static void printNumbers(Stream<Integer> numbers, int n, int k) {
        numbers.skip(k - 1).limit(n).forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stream<Integer> elements = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt);

        List<Integer> parameters = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        printNumbers(elements, parameters.get(0), parameters.get(1));
    }
}
