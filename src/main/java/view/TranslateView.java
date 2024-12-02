package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.InMemoryUserDataAccessObject;
import entity.Translate;
import entity.User;
import interface_adapter.bookmark.BookmarkState;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.feedback.FeedbackViewModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.translate.TranslateController;
import interface_adapter.translate.TranslateState;
import interface_adapter.translate.TranslateViewModel;
import interface_adapter.bookmark.BookmarkViewModel;
import interface_adapter.bookmark.BookmarkController;
import org.json.JSONArray;
import org.json.JSONString;
import use_case.change_password.ChangePasswordInputData;
import use_case.history.HistoryUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

/**
 * The View for when the user is logged into the program.
 */
public class TranslateView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final TranslateViewModel translateViewModel;
    private final HistoryViewModel historyViewModel;
    private final ChangePasswordViewModel changePasswordViewModel;
    private final BookmarkViewModel bookmarkViewModel;
  
    private final JLabel passwordErrorField = new JLabel();
    private ChangePasswordController changePasswordController;
    private LogoutController logoutController;
    private TranslateController translateController;
    private HistoryController historyController;
    private BookmarkController bookmarkController;

    private final JLabel username;

    private final JButton changePassword;
    private final JButton logOut;
    private final JButton translate;
    private final JButton history;
    private final JButton feedback;
    private final JButton bookmark;
    private final JButton addBookmark;

    private final JTextField languageInputField = new JTextField(15);
    private final JTextField languageOutputField = new JTextField(15);

    public TranslateView(LoggedInViewModel loggedInViewModel, TranslateViewModel translateViewModel,
                         HistoryViewModel historyViewModel, FeedbackViewModel feedbackViewModel, 
                         ChangePasswordViewModel changePasswordViewModel, BookmarkViewModel bookmarkViewModel) {

        this.loggedInViewModel = loggedInViewModel;
        this.translateViewModel = translateViewModel;
        this.historyViewModel = historyViewModel;
        this.changePasswordViewModel = changePasswordViewModel;
        this.bookmarkViewModel = bookmarkViewModel;
      
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.translateViewModel.addPropertyChangeListener(this);
        this.historyViewModel.addPropertyChangeListener(this);
        this.changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Translator Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        final LabelTextPanel languageInput = new LabelTextPanel(
//                new JLabel(""), languageInputField);
//
//        final LabelTextPanel languageOutput = new LabelTextPanel(
//                new JLabel(""), languageOutputField);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

//        final JLabel languageInfo = new JLabel("Language");
//        final JLabel translatedLanguageInfo = new JLabel("             Language");

        // final JLabel sourceLanguageLabel = new JLabel("Source Language: ");
        // final JLabel translatedLanguageLabel = new JLabel("Translated Language: ");

        // final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        this.add(logOut);

        translate = new JButton("Translate");
        this.add(translate);

        history = new JButton("History");
        this.add(history);

        feedback = new JButton("Feedback");
        this.add(feedback);
      
        changePassword = new JButton("Change Password");
        this.add(changePassword);

        bookmark = new JButton("Bookmark");

        addBookmark = new JButton("Add Bookmark");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        final String[] languages = {"Danish", "German", "English", "Spanish", "French", "Italian", "Japanese", "Korean",
                "Norwegian Bokmål", "Dutch", "Polish", "Portuguese", "Romanian", "Russian", "Swedish", "Chinese"};
        final String[] codes = {"DA", "DE", "EN", "ES", "FR", "IT", "JA", "KO", "NB", "NL", "PL", "PT", "RO", "RU",
                "SV", "ZH"};
        final String[] codes2 = {"DA", "DE", "EN-US", "ES", "FR", "IT", "JA", "KO", "NB", "NL", "PL", "PT", "RO", "RU",
                "SV", "ZH"};

        Map<String, String> languagesToCodes = new HashMap<>();
        Map<String, String> languagesToCodes2 = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            languagesToCodes.put(languages[i], codes[i]);
            languagesToCodes2.put(languages[i], codes2[i]);
        }
        // final List<String> languageslist = new ArrayList<>(Arrays.asList(languages));
        final JComboBox sourceLanguageBox = new JComboBox();
        final JComboBox resultLanguageBox = new JComboBox();
        for (String language: languages) {
            sourceLanguageBox.addItem(language);
            resultLanguageBox.addItem(language);
        }

        sourceLanguageBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final TranslateState currentState = translateViewModel.getState();
                currentState.setInputLanguage(languagesToCodes.get(sourceLanguageBox.getSelectedItem()));

//                final LoginUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
//                final User user = userDataAccessObject.get(currentState.getUsername());
//                this.translateController.execute(user);
            }
        });

        resultLanguageBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final TranslateState currentState = translateViewModel.getState();
                currentState.setOutputLanguage(languagesToCodes2.get(resultLanguageBox.getSelectedItem()));
//                translateController.execute("English (American)", currentState.getOutputLanguage(),
//                        currentState.getInputText());
            }
        });

        final JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstRow.add(sourceLanguageBox);
        firstRow.add(translate);
        firstRow.add(resultLanguageBox);

        final JPanel seconedRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seconedRow.add(languageInputField);
        seconedRow.add(languageOutputField);

        final JPanel thirdRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        thirdRow.add(addBookmark);
        thirdRow.add(bookmark);

        languageInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final TranslateState currentState = translateViewModel.getState();
                currentState.setInputText(languageInputField.getText());
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


//        languageOutputField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final LoggedInState currentState = loggedInViewModel.getState();
//                currentState.setPassword(languageOutputField.getText());
//                loggedInViewModel.setState(currentState);
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();
//            }
//        });

        translate.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(translate)) {
                        final TranslateState currentState = translateViewModel.getState();

                        translateController.execute(
                                currentState.getInputLanguage(),
                                currentState.getOutputLanguage(), currentState.getInputText()
                        );

                    }
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

        history.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(history)) {
                            final LoggedInState currentState = loggedInViewModel.getState();
                            final LoginUserDataAccessInterface userDataAccessObject = new InMemoryUserDataAccessObject();
                            final User user = userDataAccessObject.get(currentState.getUsername());
                            historyController.execute(user);
                        }
                    }
                }
        );

        bookmark.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changePasswordController.switchToBookmarkView();
                    }
                }
        );

        changePassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(changePassword)) {
                            final LoggedInState currentState = loggedInViewModel.getState();
                            changePasswordController.execute(currentState.getPassword(), currentState.getUsername());
                        }
                    }
                }
        );

        feedback.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        changePasswordController.switchToFeedbackView();
                    }
                }
        );
      
        addBookmark.addActionListener(evt -> {
            final TranslateState currentState = translateViewModel.getState();
            final String name = loggedInViewModel.getState().getUsername();
            final String inputLanguage = translateViewModel.getState().getInputLanguage();
            final String outputLanguage = translateViewModel.getState().getOutputLanguage();
            final String inputText = translateViewModel.getState().getInputText();

            if (currentState != null && currentState.getInputText() != null) {
                bookmarkController.addBookmark(name, inputLanguage, outputLanguage, inputText);
                JOptionPane.showMessageDialog(this, "Bookmark saved successfully!");

            }
            else {
                JOptionPane.showMessageDialog(this, "Please enter text to translate before bookmarking.");
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);

        this.add(firstRow);

        this.add(seconedRow);

        this.add(thirdRow);

        this.add(logOut);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("login")) {
            final LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
        if (evt.getPropertyName().equals("state")) {
            final TranslateState state = (TranslateState) evt.getNewValue();
            languageOutputField.setText(state.getOutputText());
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

    public void setTranslateController(TranslateController translateController) {
        this.translateController = translateController;
    }

    public void setBookmarkController(BookmarkController bookmarkController) {
        this.bookmarkController = bookmarkController;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }

}
