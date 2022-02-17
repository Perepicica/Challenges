package stream.stepic.returning_function_and_carrying;

import java.util.function.Function;
import java.util.function.Predicate;

class ExtensionService {

    /*
    Implement the addExtension method that accepts two string predicates and returns a function.
    The returning function should accept one argument
    and return the argument with .xml suffix if the argument matches the first predicate,
    .json suffix if the argument matches the second predicate, and the argument itself otherwise.
     */
    public static Function<String, String> addExtension(Predicate<String> first, Predicate<String> second) {
        return (str) -> first.test(str) ? str + ".xml" : (second.test(str) ? str + ".json" : str);
    }
}