package entity;

/**
 * Represents a Bookmark for a translation.
 */
public class Bookmark {
    private final String username;
    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;

    /**
     * Constructs a Bookmark object.
     *
     * @param username The username of the user who owns the bookmark.
     * @param inputLanguage The ID of the translation being bookmarked.
     * @param outputLanguage ID of the translation being bookmarked.
     * @param inputText The ID of the translation being bookmarked.
     */
    public Bookmark(String username, String inputLanguage, String outputLanguage, String inputText) {
        this.username = username;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
    }

    /**
     * Gets the username of the user who owns the bookmark.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

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
