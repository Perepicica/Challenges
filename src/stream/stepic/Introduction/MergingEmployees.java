package stream.stepic.Introduction;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MergingEmployees {
    /*
    For some historical reason, your company stores first names of employees on two lists.
    You were asked to end this and merge both the lists into one.
    In addition, there is an additional requirement
    that the company wants you to sort this new list in the alphabetical order.
     */
    public static Stream<String> mergeEmployees(Stream<String> empls1, Stream<String> empls2) {
        return Stream.concat(empls1, empls2).sorted();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> employees1 = convertLineToEmployees(scanner.nextLine());
        List<String> employees2 = convertLineToEmployees(scanner.nextLine());

        mergeEmployees(employees1.stream(), employees2.stream()).forEach(System.out::println);
    }

    private static List<String> convertLineToEmployees(String line) {
        return Arrays.stream(line.split("\\s+")).collect(Collectors.toList());
    }
}
