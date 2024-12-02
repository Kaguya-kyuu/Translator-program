package use_case.feedback;

/**
 * The input data for the Feedback Use Case.
 */
public class FeedbackInputData {

    private final String username;
    private final String feedback;

    public FeedbackInputData(String username, String feedback) {
        this.username = username;
        this.feedback = feedback;
    }

    public String getUsername() {
        return username;
    }

    public String getFeedback() {
        return feedback;
    }
}
