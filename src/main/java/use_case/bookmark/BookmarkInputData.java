package use_case.bookmark;

/**
 * The input data for the Bookmark Use Case.
 * Contains the information required to perform a bookmark action.
 */
public class BookmarkInputData {
    private final String username;
    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;

    /**
     * Constructs a new BookmarkInputData object.
     *
     * @param username The username of the user performing the action.
     * @param inputLanguage input language of the translation to be bookmarked or removed.
     * @param outputLanguage output language of the translation to be bookmarked or removed.
     * @param inputText The input text of the translation to be bookmarked or removed.
     */
    public BookmarkInputData(String username, String inputLanguage, String outputLanguage, String inputText) {
        this.username = username;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
    }

    /**
     * Gets the username of the user performing the action.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the ID of the translation to be bookmarked or removed.
     *
     * @return The translation ID.
     */
    public String getInputLanguage() {
        return inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }

    public String getInputText() {
        return inputText;
    }
}
