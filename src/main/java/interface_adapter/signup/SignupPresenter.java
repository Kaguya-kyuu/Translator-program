package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final TranslateViewModel translateviewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           TranslateViewModel translateviewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.translateviewModel = translateviewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.
        final TranslateState translateState = translateviewModel.getState();
        translateState.setUsername(response.getUsername());
        this.translateviewModel.setState(translateState);
        translateviewModel.firePropertyChanged();

        viewManagerModel.setState(translateviewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToTranslateView() {
        viewManagerModel.setState(translateviewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
