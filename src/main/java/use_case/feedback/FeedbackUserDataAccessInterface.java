package use_case.feedback;

import entity.Feedback;

/**
 * The interface of the DAO for the Feedback Use Case.
 */
public interface FeedbackUserDataAccessInterface {

    /**
     * Saves feedback.
     * @param feedback the feedback to save
     */
    void saveFeedback(Feedback feedback);
}
