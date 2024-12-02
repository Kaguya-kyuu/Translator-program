package entity;

/**
 * Class represents Feedback for a translation.
 */
public class Feedback {
    private final String username;
    private final String feedback;

    public Feedback(String username, String feedback) {
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
