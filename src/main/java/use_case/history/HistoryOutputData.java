package use_case.history;

import entity.User;

/**
 * Output Data for the History Use Case.
 */
public class HistoryOutputData {
    private final User user;
    private final boolean useCaseFailed;

    public HistoryOutputData(User user, boolean useCaseFailed) {
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() {
        return user;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
