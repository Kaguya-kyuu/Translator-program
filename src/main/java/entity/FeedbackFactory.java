package entity;

/**
 * Factory for creating Feedback objects.
 */
public class FeedbackFactory {

    public Feedback create(String username, String feedback) {
        return new Feedback(username, feedback);
    }
}
