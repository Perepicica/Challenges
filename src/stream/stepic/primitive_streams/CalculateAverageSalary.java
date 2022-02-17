package stream.stepic.primitive_streams;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class CalculateAverageSalary {

    /*
    Imagine that you need to calculate the average monthly salary in a company.
    For that purpose, you need to implement the calcAverageSalary method
    that takes a list of salaries and calculates the value. You don't need to round the result here.
     */
    private static double calcAverageSalary(Collection<Integer> salaries) {
        return salaries.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> salaries = scanner.tokens()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(calcAverageSalary(salaries));
    }
}