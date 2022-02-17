package stream.stepic.infinitive_streams;

import java.util.stream.Stream;

final class Utils {

    private Utils() {
    }

    /*
    Need to implement a method named generateUsers that generates the specified number of users.
    A user has an id and an email, and the values of these fields must be
    unique among the generated users (without duplicates)
    If any users have the same id or email, the tests will fail.
     */
    public static Stream<User> generateUsers(int numberOfUsers) {
        return Stream.iterate(1, i -> i <= numberOfUsers, i -> i + 1)
                .map(i -> new User(i, "user" + i + "@gmail.com"));
    }
}

class User {
    private final long id;
    private final String email;

    User(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}