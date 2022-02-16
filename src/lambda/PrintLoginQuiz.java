package lambda;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

class PrintLoginQuiz {
    /*
    Implement a method that finds a user by a specified account id.
    If the user's account type is "pro", the method should print a user's login.
    Note that you can copy the findUserByAccountId(Set<User> users, String id) method implemented previously.
     */
    public static void printLoginIfPro(Set<User1> users, String id) {
        Optional<User1> userByAccountId = findUserByAccountId(users, id);
        userByAccountId
                .flatMap(User1::getAccount)
                .filter(account1 -> account1.getType().equals("pro"))
                .ifPresent(account1 -> System.out.println(userByAccountId.get().getLogin()));
    }

    public static Optional<User1> findUserByAccountId(Set<User1> users, String id) {
        for (User1 user:users)
            if (user.getAccount().isPresent() && user.getAccount().get().getId().equals(id)) {
                return Optional.of(user);
            }
        return Optional.empty();
    }
}

class Account {
    private final String id;
    private final String type;

    public Account(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}

class User1 {
    private final String login;
    private final Account account;

    public User1(String login, Account account) {
        this.login = login;
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public Optional<Account> getAccount() {
        return Optional.ofNullable(account);
    }

    public static void main(String[] args) {
        Account account1 = new Account("Account1", "pro");
        Account account2 = new Account("Account2", "pro");
        Account account3 = new Account("Account3", "simple");
        Set<User1> usersSet = new HashSet<>();
        usersSet.add(new User1("User1", account1));
        usersSet.add(new User1("User2", account2));
        usersSet.add(new User1("User3", account3));
        PrintLoginQuiz.printLoginIfPro(usersSet, "Account1");//print User1
        PrintLoginQuiz.printLoginIfPro(usersSet, "Account3");//doesn't print anything
        PrintLoginQuiz.printLoginIfPro(usersSet, "Account22");//doesn't print anything
    }
}
