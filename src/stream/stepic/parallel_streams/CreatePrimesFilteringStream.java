package stream.stepic.parallel_streams;


import java.util.stream.LongStream;

public class CreatePrimesFilteringStream {

    /*
    Need to create a prepared parallel LongStream for filtering prime numbers in the given range
    (inclusive both borders).
    After calling the count() method it should return the count of prime numbers in the given range.
     */
    public static LongStream createPrimesFilteringStream(long start, long end) {
        return LongStream.rangeClosed(start, end)
                .parallel()
                .filter(NumberUtils::isPrime);
    }

    static class NumberUtils {
        public static boolean isPrime(long n) {
            return n > 1 && LongStream
                    .rangeClosed(2, n - 1)
                    .noneMatch(divisor -> n % divisor == 0);
        }
    }
}

