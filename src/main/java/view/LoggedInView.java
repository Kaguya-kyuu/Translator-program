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

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final TranslateViewModel translateViewModel;
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private TranslateController translateController;

    private final JLabel username;

    private final JButton logOut;
    private final JButton translate;
    private final JButton history;
    private final JButton toBookmarkButton;

    private final JTextField languageInputField = new JTextField(15);
    private final JTextField languageOutputField = new JTextField(15);
    private final JTextField inputTextField = new JTextField(20);
    private final JTextArea translatedTextArea = new JTextArea(5, 20);

    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.translateViewModel = new TranslateViewModel();

        final JLabel title = new JLabel("Translator Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel languageInput = new LabelTextPanel(
                new JLabel(""), languageInputField);

        final LabelTextPanel languageOutput = new LabelTextPanel(
                new JLabel(""), languageOutputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        final JLabel languageInfo = new JLabel("Language");
        final JLabel translatedLanguageInfo = new JLabel("             Language");

        final JLabel sourceLanguageLabel = new JLabel("Source Language: ");
        final JLabel translatedLanguageLabel = new JLabel("Translated Language: ");

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
                        changePasswordController.switchToBookmarkView();
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstRow.add(sourceLanguageLabel);
        firstRow.add(buttons.getComponent(1));
        firstRow.add(translatedLanguageLabel);

        final JPanel seconedRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seconedRow.add(languageInput);
        seconedRow.add(languageOutput);

        final JPanel thiredRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thiredRow.add(languageInfo);
        thiredRow.add(translatedLanguageInfo);

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

        translate.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(translate)) {
                        final TranslateState currentState = translateViewModel.getState();

                        this.translateController.execute(currentState.getInputLanguage(),
                                currentState.getOutputLanguage(), currentState.getInputText());
                        translatedTextArea.setText(currentState.getOutputText());

                    }

                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            // 1. get the state out of the loggedInViewModel. It contains the username.
                            // 2. Execute the logout Controller.
                            final LoggedInState currentState = loggedInViewModel.getState();

                            logoutController.execute(
                                    currentState.getUsername()
                            );
                        }
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(firstRow);

        this.add(seconedRow);

        this.add(thiredRow);

        this.add(buttons);
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

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public class ChangePasswordView extends JPanel {
        public ChangePasswordView() {
            this.setLayout(new GridLayout(2, 1));

            // Other components here...

            JButton changePasswordButton = new JButton("Change Password");
            changePasswordButton.addActionListener(e -> {
                ViewManager.getInstance().navigateTo(new ChangePasswordView());
            });
            this.add(changePasswordButton);
        }
    }
}
