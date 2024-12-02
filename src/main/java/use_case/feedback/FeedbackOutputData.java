package use_case.feedback;

/**
 * Output Data for the Feedback Use Case.
 */
public class FeedbackOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public FeedbackOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
