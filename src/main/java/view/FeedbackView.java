package view;

import interface_adapter.feedback.FeedbackController;
import interface_adapter.feedback.FeedbackState;
import interface_adapter.feedback.FeedbackViewModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FeedbackView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "feedback";
    private final FeedbackViewModel feedbackViewModel;
    private FeedbackController feedbackController;

    private final JButton addFeedbackButton;
    private final JButton backButton;

    private final JTextField feedbackInputField = new JTextField(15);

    public FeedbackView(FeedbackViewModel feedbackViewModel) {
        this.feedbackViewModel = feedbackViewModel;
        this.feedbackViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(FeedbackViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel feedbackInfo = new LabelTextPanel(
                new JLabel(FeedbackViewModel.INPUT_FEEDBACK_LABEL), feedbackInputField);

        final JPanel buttons = new JPanel();
        addFeedbackButton = new JButton(FeedbackViewModel.FEEDBACK_BUTTON_LABEL);
        buttons.add(addFeedbackButton);
        backButton = new JButton(FeedbackViewModel.BACK_LABEL);
        buttons.add(backButton);

        addFeedbackButton.addActionListener(evt -> {
            final FeedbackState currentState = feedbackViewModel.getState();
            if (currentState != null && currentState.getFeedback() != null) {
                feedbackController.execute(currentState.getUsername(), currentState.getFeedback());
            }
        });

        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        feedbackController.switchBackToTranslateView();
                    }
                }
        );

        addFeedbackListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(feedbackInfo);
        this.add(buttons);
    }

    private void addFeedbackListener() {
        feedbackInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final FeedbackState currentState = feedbackViewModel.getState();
                currentState.setFeedback(feedbackInputField.getText());
                feedbackViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final FeedbackState state = (FeedbackState) evt.getNewValue();
        if (state.getFeedbackError() != null) {
            JOptionPane.showMessageDialog(this, state.getFeedbackError());
        }
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
    }

    public void setFeedbackController(FeedbackController controller) {
        this.feedbackController = controller;
    }
}
