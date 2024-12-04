package interface_adapter.feedback;

public class FeedbackState {
    private String username = "";
    private String feedback = "";
    private String feedbackError;

    public FeedbackState(FeedbackState copy) {
        username = copy.username;
        feedback = copy.feedback;
        feedbackError = copy.feedbackError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public FeedbackState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbackError() {
        return feedbackError;
    }

    public void setFeedbackError(String feedbackError) {
        this.feedbackError = feedbackError;
    }
}
