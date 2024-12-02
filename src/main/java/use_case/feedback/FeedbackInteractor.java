package use_case.feedback;

import entity.Feedback;
import entity.FeedbackFactory;

/**
 * The Feedback Interactor.
 */
public class FeedbackInteractor implements FeedbackInputBoundary {
    private final FeedbackUserDataAccessInterface feedbackDataAccess;
    private final FeedbackOutputBoundary feedbackPresenter;
    private final FeedbackFactory feedbackFactory;

    public FeedbackInteractor(FeedbackUserDataAccessInterface feedbackDataAccess,
                              FeedbackOutputBoundary feedbackPresenter, FeedbackFactory feedbackFactory) {
        this.feedbackDataAccess = feedbackDataAccess;
        this.feedbackPresenter = feedbackPresenter;
        this.feedbackFactory = feedbackFactory;
    }

    @Override
    public void execute(FeedbackInputData feedbackInputData) {
        final String username = feedbackInputData.getUsername();
        final String feedbackMessage = feedbackInputData.getFeedback();

        final Feedback feedback = feedbackFactory.create(username, feedbackMessage);

        if (feedback == null) {
            feedbackPresenter.prepareFailView("Feedback cannot be empty.");
        }

        else {
            feedbackDataAccess.saveFeedback(feedback);

            final FeedbackOutputData outputData = new FeedbackOutputData(username, false);
            feedbackPresenter.prepareSuccessView(outputData);
        }

    }

    /**
     * Switch back to Translate View.
     */
    @Override
    public void switchBackToTranslateView() {
        feedbackPresenter.switchBackToTranslateView();
    }

}
