package use_case.feedback;

/**
 * Input Boundary for actions which are related to sending feedback.
 */
public interface FeedbackInputBoundary {

    /**
     * Execute the Feedback Use Case.
     * @param feedbackInputData the input data for this use case
     */
    void execute(FeedbackInputData feedbackInputData);

    /**
     * Executes going back to Translate view.
     */
    void switchBackToTranslateView();
}
