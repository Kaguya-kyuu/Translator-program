package interface_adapter.history;

import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

/**
 * The presenter for the History Use Case.
 */
public class HistoryPresenter implements HistoryOutputBoundary {

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
}
