package stream.stepic.map_flatmap;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Countries {

    /*
    implement the getNamesOfTop3PopulatedCountries to return a list that contains
    three country names which have the largest populations from all countries of the original collection.
    The names must be in uppercase and sorted according to the population size
    (from the largest to the smallest population).
     */
    public static List<String> getNamesOfTop3PopulatedCountries(Collection<Country> countries) {
        return countries.stream()
                .sorted(Comparator.comparing(Country::getPopulation).reversed())
                .limit(3)
                .map(Country::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Country> countries = Stream.iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\s+");
                    return new Country(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());

        getNamesOfTop3PopulatedCountries(countries)
                .forEach(System.out::println);
    }
}

class Country {
    private final String name;
    private final int population;

    Country(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}