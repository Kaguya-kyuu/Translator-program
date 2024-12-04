package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import entity.Bookmark;
import interface_adapter.bookmark.BookmarkController;
import interface_adapter.bookmark.BookmarkState;
import interface_adapter.bookmark.BookmarkViewModel;

/**
 * The View for the Bookmark Use Case.
 */
public class BookmarkView extends JPanel implements PropertyChangeListener {

    private final String viewName = "bookmark";
    private final BookmarkViewModel bookmarkViewModel;
    private BookmarkController bookmarkController;

    private final JLabel bookmarkLabel;
    private final JList bookmarkList;
    private final DefaultListModel listModel;

    private final JButton addBookmarkButton;
    private final JButton removeBookmarkButton;
    private final JButton backButton;

    public BookmarkView(BookmarkViewModel bookmarkViewModel) {
        this.bookmarkViewModel = bookmarkViewModel;
        this.bookmarkViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(BookmarkViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        bookmarkLabel = new JLabel("Your saved bookmarks:");
        listModel = new DefaultListModel<>();
        bookmarkList = new JList<>(listModel);

        final JPanel buttons = new JPanel();
        addBookmarkButton = new JButton(BookmarkViewModel.ADD_BOOKMARK_BUTTON_LABEL);
        buttons.add(addBookmarkButton);
        removeBookmarkButton = new JButton(BookmarkViewModel.REMOVE_BOOKMARK_BUTTON_LABEL);
        buttons.add(removeBookmarkButton);
        backButton = new JButton(BookmarkViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);

        addBookmarkButton.addActionListener(evt -> {
            final BookmarkState currentState = bookmarkViewModel.getState();
            if (currentState != null && currentState.getInputText() != null) {
                bookmarkController.addBookmark(currentState.getUsername(),
                        currentState.getInputLanguage(),
                        currentState.getOutputLanguage(),
                        currentState.getInputText());
            }
        });

        removeBookmarkButton.addActionListener(evt -> {
            final BookmarkState currentState = bookmarkViewModel.getState();
            if (currentState != null && currentState.getInputText() != null) {
                bookmarkController.removeBookmark(currentState.getUsername(),
                        currentState.getInputLanguage(),
                        currentState.getOutputLanguage(),
                        currentState.getInputText());
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookmarkController.switchToTranslateView();
            }

            // Go back to the previous view
        });

        // Layout and adding components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(bookmarkLabel);
        this.add(new JScrollPane(bookmarkList)); // Adding the scrollable list
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("logged in")) {
            List<Bookmark> bookmarks = (List<Bookmark>) evt.getNewValue();
            listModel.clear(); // Clear current list
            for (Bookmark bookmark : bookmarks) {
                listModel.addElement(bookmark.toString()); // Update the list with new bookmarks
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setBookmarkController(BookmarkController controller) {
        this.bookmarkController = controller;
    }
}
