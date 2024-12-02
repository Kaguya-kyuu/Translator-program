package interface_adapter.feedback;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.feedback.FeedbackOutputBoundary;
import use_case.feedback.FeedbackOutputData;

/**
 * The presenter for the Feedback Use Case.
 */
public class FeedbackPresenter implements FeedbackOutputBoundary {

    private final FeedbackViewModel feedbackViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public FeedbackPresenter(ViewManagerModel viewManagerModel,
                             FeedbackViewModel feedbackViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.feedbackViewModel = feedbackViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Prepare success view.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(FeedbackOutputData outputData) {
        final FeedbackState feedbackState = feedbackViewModel.getState();
        feedbackState.setUsername(outputData.getUsername());
        this.feedbackViewModel.setState(feedbackState);
        this.feedbackViewModel.firePropertyChanged();

        this.viewManagerModel.setState(feedbackViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare fail view.
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        final FeedbackState feedbackState = feedbackViewModel.getState();
        feedbackState.setFeedbackError(error);
        feedbackViewModel.firePropertyChanged();
    }

    public void switchBackToTranslateView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
