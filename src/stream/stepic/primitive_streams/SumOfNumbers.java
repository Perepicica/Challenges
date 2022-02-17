package stream.stepic.primitive_streams;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.LongStream;

class SumOfNumbers {

    /*
    Need to implement the sum method that calculates the sum of the given array of long's.
     */
    public static long sum(long[] numbers) {
        return LongStream.of(numbers).sum();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();

        System.out.println(sum(numbers));
    }
}