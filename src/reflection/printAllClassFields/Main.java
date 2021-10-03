package reflection.printAllClassFields;

import java.lang.reflect.Field;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        printAllClassFields(TestClass.class);
    }

    /**
     * определяются и выводятся в консоль все наименования полей класса;
     * наименования полей класса должны быть отсортированы по алфавиту.
     * @param aClass class definition
     */
    private static void printAllClassFields(Class aClass) {
        List.of(aClass.getDeclaredFields())
                .stream()
                .map(Field::getName)
                .sorted()
                .forEach(System.out::println);

    }
}
