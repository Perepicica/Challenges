package lambda;

import java.util.function.Predicate;

public class Xor {
    /**
     * create the xor operation using all of and, or and not
     */
    public static <T> Predicate<T> xor(Predicate<T> predicate1, Predicate<T> predicate2) {
        return t -> (predicate1.or(predicate2)).and((predicate1.and(predicate2)).negate()).test(t);
    }
}
