package use_case.bookmark;

/**
 * Output data for the Bookmark Use Case.
 */
public class BookmarkOutputData {
    private final String username;
    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;
    private final String message;

    public BookmarkOutputData(String username, String inputLanguage,
                              String outputLanguage,
                              String inputText,
                              String message) {
        this.username = username;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
        this.message = message;
    }

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

    public String getMessage() {
        return message;
    }
}
