package interface_adapter.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.bookmark.BookmarkViewModel;
import interface_adapter.feedback.FeedbackViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final BookmarkViewModel bookmarkViewModel;
    private final FeedbackViewModel feedbackViewModel;

    public ChangePasswordPresenter(LoggedInViewModel loggedInViewModel,
                                   ViewManagerModel viewManagerModel,
                                   BookmarkViewModel bookmarkViewModel, FeedbackViewModel feedbackViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.bookmarkViewModel = bookmarkViewModel;
        this.feedbackViewModel = feedbackViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // currently there isn't anything to change based on the output data,
        // since the output data only contains the username, which remains the same.
        // We still fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully..
        loggedInViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    @Override
    public void switchToBookmarkView() {
        viewManagerModel.setState(bookmarkViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFeedbackView() {
        viewManagerModel.setState(feedbackViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
