package interface_adapter.bookmark;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.bookmark.BookmarkOutputBoundary;
import use_case.bookmark.BookmarkOutputData;

/**
 * The Presenter for the Bookmark Use Case.
 * Converts output data to a format suitable for the ViewModel.
 */
public class BookmarkPresenter implements BookmarkOutputBoundary {

    private final BookmarkViewModel bookmarkViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;
    /**
     * Constructs a BookmarkPresenter with the given ViewModel.
     *
     * @param bookmarkViewModel The ViewModel to update based on the output data.
     */
    public BookmarkPresenter(BookmarkViewModel bookmarkViewModel,
                             ViewManagerModel viewManagerModel,
                             LoggedInViewModel loggedInViewModel) {
        this.bookmarkViewModel = bookmarkViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(BookmarkOutputData outputData) {
    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    @Override
    public void switchToTranslateView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
