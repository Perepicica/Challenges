package stream.stepic.collecting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Competition {

    /**
     * There are a lot of teams which take part in this competition
     * and every team presents their own project.
     * <p>
     * You need to implement the getTeamPlaceMap that accepts an unordered collection of teams
     * and returns a map containing team names as keys and their positions as values in the sorted order
     * (from the best to the worst position).
     * <p>
     * All teams are implemented as objects of the Team class
     * and this class provides the team name and the place in the competition.
     * There is also a guarantee that all team names are unique in this competition.
     */
    public static Map<String, Integer> getTeamPlaceMap(Collection<Team> teams) {
        return teams.stream()
                .sorted(Comparator.comparing(Team::getPlace))
                .collect(Collectors.toMap(Team::getName, Team::getPlace, (f, s) -> f, LinkedHashMap::new));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Collection<Team> teams = Stream
                .iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine().split("\\s+"))
                .map(parts -> new Team(parts[0], Integer.parseInt(parts[1])))
                .collect(Collectors.toSet());

        getTeamPlaceMap(teams)
                .forEach((team, speaker) -> System.out.println(team + ": " + speaker));
    }
}

class Team {
    private final String name;
    private final int place;

    public Team(String name, int place) {
        this.name = name;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }
}