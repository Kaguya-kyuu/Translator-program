package interface_adapter.bookmark;

/**
 * Represents the state of the Bookmark feature.
 * Stores the username, translation ID, and any status messages.
 */
public class BookmarkState {
    private String username;
    private String inputLanguage;
    private String outputLanguage;
    private String inputText;
    private String statusMessage;

    /**
     * Constructs a BookmarkState with the given username, translation ID, and status message.
     *
     * @param username The username associated with the current state.
     * @param inputLanguage The input language of the translation.
     * @param outputLanguage The output language of the translation.
     * @param inputText The input text of the translation.
     * @param statusMessage The status message for the bookmark operation.
     */
    public BookmarkState(String username, String inputLanguage, String outputLanguage, String inputText,
                         String statusMessage) {
        this.username = username;
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
        this.statusMessage = statusMessage;
    }

    public BookmarkState(BookmarkState copy) {
        this.username = copy.username;
        this.inputLanguage = copy.inputLanguage;
        this.outputLanguage = copy.outputLanguage;
        this.inputText = copy.inputText;
        this.statusMessage = copy.statusMessage;
    }

    // Default constructor.
    public BookmarkState() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInputLanguage() {
        return inputLanguage;
    }

    public void setInputLanguage(String inputLanguage) {
        this.inputLanguage = inputLanguage;
    }

    public String getOutputLanguage() {
        return outputLanguage;
    }

    public void setOutputLanguage(String outputLanguage) {
        this.outputLanguage = outputLanguage;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
