package interface_adapter.bookmark;

import use_case.bookmark.BookmarkInputBoundary;
import use_case.bookmark.BookmarkInputData;

/**
 * Controller for the Bookmark Use Case.
 * Receives input from the UI and passes it to the use case interactor.
 */
public class BookmarkController {
    private final BookmarkInputBoundary bookmarkInputBoundary;

    /**
     * Constructs a BookmarkController with the given input boundary.
     *
     * @param bookmarkInputBoundary The use case interactor to handle bookmark operations.
     */
    public BookmarkController(BookmarkInputBoundary bookmarkInputBoundary) {
        this.bookmarkInputBoundary = bookmarkInputBoundary;
    }

    /**
     * Adds a bookmark for the given translation.
     *
     * @param username The username of the user adding the bookmark.
     * @param inputLanguage input language of the translation to be bookmarked.
     * @param outputLanguage output language of the translation to be bookmarked.
     * @param inputText The input text of the translation to be bookmarked.
     */
    public void addBookmark(String username, String inputLanguage, String outputLanguage, String inputText) {
        final BookmarkInputData inputData = new BookmarkInputData(username, inputLanguage, outputLanguage, inputText);
        bookmarkInputBoundary.addBookmark(inputData);
    }

    /**
     * Removes a bookmark for the given translation.
     *
     * @param username The username of the user removing the bookmark.
     * @param inputLanguage input language of the translation to be removed.
     * @param outputLanguage output language of the translation to be removed.
     * @param inputText The input text of the translation to be removed.
     */
    public void removeBookmark(String username, String inputLanguage, String outputLanguage, String inputText) {
        final BookmarkInputData inputData = new BookmarkInputData(username, inputLanguage, outputLanguage, inputText);
        bookmarkInputBoundary.removeBookmark(inputData);
    }

    /**
     * Executes the "switch to TranslateView" Use Case.
     */
    public void switchToTranslateView() {
        bookmarkInputBoundary.switchToTranslateView();
    }
}
