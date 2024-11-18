package interface_adapter.translate;

import interface_adapter.ViewManagerModel;
import use_case.translator.TranslatorOutputBoundary;
import use_case.translator.TranslatorOutputData;

/**
 * The presenter for the Translate Use Case.
 */
public class TranslatePresenter implements TranslatorOutputBoundary {

    private final TranslateViewModel translateViewModel;
    private final TranslatedViewModel translatedViewModel;
    private final ViewManagerModel viewManagerModel;

    public TranslatePresenter(ViewManagerModel viewManagerModel,
                          TranslatedViewModel translatedViewModel,
                          TranslateViewModel translateViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.translatedViewModel = translatedViewModel;
        this.translateViewModel = translateViewModel;
    }

    /**
     * Prepare success view.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(TranslatorOutputData outputData) {
        final TranslateState translateState = translatedViewModel.getState();
        translateState.setOutputText(outputData.getOutputText());
        this.translatedViewModel.setState(translatedState);
        this.translatedViewModel.firePropertyChanged();

        this.viewManagerModel.setState(translatedViewModel.getViewName());
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
}
