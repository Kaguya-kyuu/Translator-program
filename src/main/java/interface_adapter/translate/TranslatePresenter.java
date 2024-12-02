package interface_adapter.translate;

import interface_adapter.ViewManagerModel;
import interface_adapter.bookmark.BookmarkState;
import interface_adapter.bookmark.BookmarkViewModel;
import use_case.translator.TranslatorOutputBoundary;
import use_case.translator.TranslatorOutputData;

/**
 * The presenter for the Translate Use Case.
 */
public class TranslatePresenter implements TranslatorOutputBoundary {

    private final TranslateViewModel translateViewModel;
    private final ViewManagerModel viewManagerModel;
    private final BookmarkViewModel bookmarkViewModel;

    public TranslatePresenter(ViewManagerModel viewManagerModel,
                          TranslateViewModel translateViewModel,
                              BookmarkViewModel bookmarkViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.translateViewModel = translateViewModel;
        this.bookmarkViewModel = bookmarkViewModel;
    }

    /**
     * Prepare success view.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(TranslatorOutputData outputData) {
        final TranslateState translateState = translateViewModel.getState();
        translateState.setOutputText(outputData.getOutputText());
        this.translateViewModel.setState(translateState);
        this.translateViewModel.firePropertyChanged();

        this.viewManagerModel.setState(translateViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare failed view.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final TranslateState translateState = translateViewModel.getState();
        translateState.setTranslationError(errorMessage);
        translateViewModel.firePropertyChanged();
    }

    @Override
    public void switchToBookmarkView() {
        viewManagerModel.setState(bookmarkViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
