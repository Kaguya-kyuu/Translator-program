package view;

import java.awt.Component;
import java.awt.FlowLayout;
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

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;

/**
 * The View for when the user is logged into the program and doing the translation.
 */
public class TranslateView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "translate";

    private final TranslateViewModel translateViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private TranslateController translateController;
    private LogoutController logoutController;

    private final JLabel username;
    private final JLabel outputText;

    private final JButton logOut;
    private final JButton translate;
    private final JButton history;
    private final JButton toBookmarkButton;

    private final JTextField languageInputField = new JTextField(15);
    private final JTextField languageOutputField = new JTextField(15);
    private final JTextField inputTextField = new JTextField(20);

    public TranslateView(TranslateViewModel translateViewModel) {
        this.translateViewModel = translateViewModel;
        this.loggedInViewModel = new LoggedInViewModel();
        this.translateViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Translator Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");

        username = new JLabel();

        outputText = new JLabel();

        final LabelTextPanel languageInput = new LabelTextPanel(
                new JLabel("Source Language: "), languageInputField);

        final LabelTextPanel languageOutput = new LabelTextPanel(
                new JLabel("     Translated Language: "), languageOutputField);

        final LabelTextPanel inputText = new LabelTextPanel(
                new JLabel("Input: "), inputTextField);

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        translate = new JButton("Translate");
        buttons.add(translate);

        history = new JButton("History");
        buttons.add(history);

        toBookmarkButton = new JButton("Bookmark");
        buttons.add(toBookmarkButton);

        toBookmarkButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        translateController.switchToBookmarkView();
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel nameRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nameRow.add(usernameInfo);
        nameRow.add(username);

        final JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstRow.add(languageInput);
        firstRow.add(buttons.getComponent(1));
        firstRow.add(languageOutput);

        final JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        secondRow.add(inputText);
        secondRow.add(outputText);

        final JPanel thiredRow = new JPanel(new FlowLayout(FlowLayout.LEFT));

        languageOutputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TranslateState currentState = TranslateView.this.translateViewModel.getState();
                currentState.setOutputLanguage(languageOutputField.getText());
                TranslateView.this.translateViewModel.setState(currentState);
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

        languageInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TranslateState currentState = TranslateView.this.translateViewModel.getState();
                currentState.setInputLanguage(languageInputField.getText());
                TranslateView.this.translateViewModel.setState(currentState);

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

        inputTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TranslateState currentState = TranslateView.this.translateViewModel.getState();
                currentState.setInputText(inputTextField.getText());
                TranslateView.this.translateViewModel.setState(currentState);

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

        translate.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(translate)) {
                        final TranslateState currentState = this.translateViewModel.getState();
                        this.translateController.execute(currentState.getInputLanguage(),
                                currentState.getOutputLanguage(), currentState.getInputText());

                        System.out.println("Translated text: " + this.translateViewModel.getState().getTranslatedText());
                        outputText.setText(this.translateViewModel.getState().getOutputText());
                    }

                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            // 1. get the state out of the loggedInViewModel. It contains the username.
                            // 2. Execute the logout Controller.
                            final TranslateState currentState = translateViewModel.getState();

                            logoutController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        addInputLanguageListerner();
        addOutputLanguageListerner();
        addInputTextListerner();

        this.add(title);

        this.add(nameRow);

        this.add(firstRow);

        this.add(secondRow);

        this.add(thiredRow);

        this.add(buttons);
    }

    private void addInputLanguageListerner() {
        languageInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TranslateState currentState = translateViewModel.getState();
                currentState.setInputLanguage(languageInputField.getText());
                translateViewModel.setState(currentState);
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

    private void addOutputLanguageListerner() {
        languageOutputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TranslateState currentState = translateViewModel.getState();
                currentState.setOutputLanguage(languageOutputField.getText());
                translateViewModel.setState(currentState);
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

    private void addInputTextListerner() {
        inputTextField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final TranslateState currentState = translateViewModel.getState();
                currentState.setInputText(inputTextField.getText());
                translateViewModel.setState(currentState);
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
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        else if (evt.getPropertyName().equals("password")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setTranslateController(TranslateController translateController) {
        this.translateController = translateController;
    }
}
