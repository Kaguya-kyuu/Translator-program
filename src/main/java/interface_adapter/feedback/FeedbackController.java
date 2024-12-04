package interface_adapter.feedback;

import use_case.feedback.FeedbackInputBoundary;
import use_case.feedback.FeedbackInputData;

/**
 * The controller for the Feedback Use Case.
 */
public class FeedbackController {
    private final FeedbackInputBoundary feedbackUseCaseInteractor;

    public FeedbackController(FeedbackInputBoundary feedbackUseCaseInteractor) {
        this.feedbackUseCaseInteractor = feedbackUseCaseInteractor;
    }

    /**
     * Executes the Feedback Use Case.
     * @param username the input language
     * @param feedback the input text
     */
    public void execute(String username, String feedback) {
        final FeedbackInputData feedbackInputData = new FeedbackInputData(username, feedback);

        feedbackUseCaseInteractor.execute(feedbackInputData);
    }

    /**
     * Executes the "switch to TranslateView" Use Case.
     */
    public void switchBackToTranslateView() {
        feedbackUseCaseInteractor.switchBackToTranslateView();
    }
}
