package use_case.feedback;

/**
 * The output boundary for the Feedback Use Case.
 */
public interface FeedbackOutputBoundary {
    /**
     * Prepares the success view for the Feedback Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FeedbackOutputData outputData);

    /**
     * Prepares the failure view for the Feedback Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    void switchBackToTranslateView();
}
