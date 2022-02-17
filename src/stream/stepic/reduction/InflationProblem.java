package stream.stepic.reduction;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Imagine you live in a country that unfortunately has a very high level of economic inflation
and some categories of groceries get significantly more expensive every year.
 */
class InflationProblem {

    /*
     Implement the method that calculates the total price of a list of groceries in N years.
     If the number of years equals to 0,
     the result must be the same as if we calculate the price without inflation.
     */
    public static long calculateTotalPriceInFuture(int numberOfYears, List<Grocery> groceries) {
        return groceries.stream()
                .mapToLong(grocery -> grocery.getCost() * (long) Math.pow(grocery.getCategory().getTimes(), numberOfYears))
                .sum();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfYears = Integer.parseInt(scanner.nextLine());

        List<Grocery> groceries = Stream.iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\s+");
                    return new Grocery(Long.parseLong(parts[0]), Category.valueOf(parts[1]));
                })
                .collect(Collectors.toList());

        long totalPriceInFuture = calculateTotalPriceInFuture(numberOfYears, groceries);

        System.out.println(totalPriceInFuture);
    }
}

enum Category {
    VEGETABLES(3),
    FRUITS(4),
    DAIRY(2);

    private final long times;

    Category(long times) {
        this.times = times;
    }

    public long getTimes() {
        return this.times;
    }
}

class Grocery {
    private final long cost;
    private final Category category;

    // Imagine that this class has some other fields but they are skipped for simplicity

    public Grocery(long cost, Category category) {
        this.cost = cost;
        this.category = category;
    }

    public long getCost() {
        return cost;
    }

    public Category getCategory() {
        return category;
    }
}