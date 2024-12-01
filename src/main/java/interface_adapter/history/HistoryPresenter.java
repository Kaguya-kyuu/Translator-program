package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.translate.TranslateViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

/**
 * The presenter for the History Use Case.
 */
public class HistoryPresenter implements HistoryOutputBoundary {
    private final TranslateViewModel translateViewModel;
    private final ViewManagerModel viewManagerModel;

    public HistoryPresenter(TranslateViewModel translateViewModel, ViewManagerModel viewManagerModel) {
        this.translateViewModel = translateViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare success view.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(HistoryOutputData outputData) {
    }

    /**
     * Prepare failed view.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
    }

    @Override
    public void switchBackToTranslateView() {
        viewManagerModel.setState(translateViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
