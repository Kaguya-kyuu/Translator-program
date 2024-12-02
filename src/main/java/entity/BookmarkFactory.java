package entity;

/**
 * Factory for creating Bookmark objects.
 */
public class BookmarkFactory {

    /**
     * Creates a new Bookmark object.
     *
     * @param username The username of the user who owns the bookmark.
     * @param inputLanguage The input language of the translation to be bookmarked.
     * @param outputLanguage The output language of the translation to be bookmarked.
     * @param inputText The inputText of the translation to be bookmarked.
     * @return A new Bookmark object.
     */
    public Bookmark create(String username, String inputLanguage, String outputLanguage, String inputText) {
        return new Bookmark(username, inputLanguage, outputLanguage, inputText);
    }
}
