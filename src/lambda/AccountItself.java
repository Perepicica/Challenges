package lambda;

import java.util.Comparator;

class AccountItself {
    private final String owner;
    private final long balance;
    private final boolean locked;

    AccountItself(String owner, long balance, boolean locked) {
        this.owner = owner;
        this.balance = balance;
        this.locked = locked;
    }

    /**
     * non-blocked accounts before the blocked
     * sorted by balance in descending order.
     * If the balances are the same, the accounts must also be sorted by owner name in lexicographic order.
     */
    public static Comparator<AccountItself> getComparatorByLockedBalanceAndOwner() {
        return Comparator
                .comparing(AccountItself::isLocked)
                .thenComparing(AccountItself::getBalance, Comparator.reverseOrder())
                .thenComparing(AccountItself::getOwner);
    }

    public String getOwner() {
        return owner;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return locked;
    }

    @Override
    public String toString() {
        return owner + " " + balance + " " + locked;
    }
}
