package interface_adapter.feedback;

import interface_adapter.ViewModel;

/**
 * The View Model for the Feedback View.
 */
public class FeedbackViewModel extends ViewModel<FeedbackState> {

    public static final String TITLE_LABEL = "Feedback";
    public static final String BACK_LABEL = "Back";

    public static final String INPUT_FEEDBACK_LABEL = "Input your feedback here";

    public static final String FEEDBACK_BUTTON_LABEL = "Submit feedback";

    public FeedbackViewModel() {
        super("feedback");
        setState(new FeedbackState());
    }
}
