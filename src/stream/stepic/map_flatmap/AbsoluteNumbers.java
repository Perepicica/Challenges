package stream.stepic.map_flatmap;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

class AbsoluteNumbers {

    /*
    There is a program that should print the absolute values of given integer numbers.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String result = Arrays.stream(scanner.nextLine().split("\\s+")) // Stream<String>
                .map(s -> s.replaceAll("-", ""))
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}