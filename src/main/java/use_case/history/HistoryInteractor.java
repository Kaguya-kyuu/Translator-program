package use_case.history;

import java.util.List;

import entity.Translate;
import entity.User;

/**
 * The History Interactor.
 */
public class HistoryInteractor implements HistoryInputBoundary {

    private final HistoryUserDataAccessInterface historyDataAccessObject;
    private final HistoryOutputBoundary historyPresenter;

    public HistoryInteractor(HistoryUserDataAccessInterface historyDataAccessObject,
                             HistoryOutputBoundary historyPresenter) {
        this.historyDataAccessObject = historyDataAccessObject;
        this.historyPresenter = historyPresenter;
    }

    /**
     * @param historyInputData the input data
     */
    @Override
    public void execute(HistoryInputData historyInputData) {
        final User user = historyInputData.getUser();
        final List<Translate> alltranslation = historyInputData.getAllTranslationsByUser();

        if (alltranslation.isEmpty()) {
            historyPresenter.prepareFailView("There is not searching history currently.");
        }

        else {
            try {
                final HistoryOutputData outputData = new HistoryOutputData(user, false);
                historyPresenter.prepareSuccessView(outputData);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                historyPresenter.prepareFailView("An error occurred. "
                        + illegalArgumentException.getMessage());
            }
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
