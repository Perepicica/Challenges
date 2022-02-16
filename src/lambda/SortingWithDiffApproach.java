package lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingWithDiffApproach {
      @Test
    void sortPersonByFirstNameUsingArraysSortAndLocalComparator() {
        Person[] people = getPeople();
        //Java 7
        class ComparatorByFirstName implements Comparator<Person> {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        }

        Arrays.sort(people, new ComparatorByFirstName());
        assertArrayEquals(people, new Person[]{
                new Person("Natasha", "C", 45),
                new Person("Pasha", "B", 25),
                new Person("Sasha", "A", 25),
        });
    }

    @Test
    void sortPersonByFirstNameUsingAnonymousComparator() {
        Person[] people = getPeople();

        Arrays.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        assertArrayEquals(people, new Person[]{
                new Person("Natasha", "C", 45),
                new Person("Pasha", "B", 25),
                new Person("Sasha", "A", 25),
        });
    }

    private Person[] getPeople() {
        Person sasha = new Person("Sasha", "A", 25);
        Person pasha = new Person("Pasha", "B", 25);
        Person natasha = new Person("Natasha", "C", 45);
        return new Person[]{sasha, pasha, natasha};
    }

    @Test
    void findFirstAlexaUsingForeach() {
        Person[] people = getPeople();
        Person person = null;

        for (Person p : people) {
            if ("Pasha".equals(p.getFirstName())) {
                person = p;
                break;
            }
        }
        assertEquals(person, new Person("Pasha", "B", 25));
    }

    @Test
    void createMapFromListUsingForeach() {
        Map<String, Person> map = new HashMap<>();
        for (Person person : getPeople()) {
            map.put(person.getLastName(), person);
        }

        assertEquals(map, (new HashMap<String, Person>() {{
            put("A", new Person("Sasha", "A", 25));
            put("B", new Person("Pasha", "B", 25));
            put("C", new Person("Natasha", "C", 45));
        }}));
    }
}
