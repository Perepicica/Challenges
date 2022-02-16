package lambda;

import java.util.Optional;
import java.util.Set;

class FindUserQuiz {

    public static Optional<User> findUserByAccountId(Set<User> users, String id) {
       for (User user:users)
            if (user.getAccount().isPresent() && user.getAccount().get().getId().equals(id)) {
                return Optional.of(user);
            }
        return Optional.empty();
    }
}


class User {
    private final String login;
    private final Account account;

    public User(String login, Account account) {
        this.login = login;
        this.account = account;
    }

    public String getLogin() {
        return login;
    }

    public Optional<Account> getAccount() {
        return Optional.ofNullable(account);
    }
}
