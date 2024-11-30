package use_case.history;

import java.util.List;
import java.util.Map;

/**
 * DAO for the History Use Case.
 */
public interface HistoryUserDataAccessInterface {

    /**
     * Get all translation history.
     * @return a list of translates result.
     */
    List<String> getAllTranslationsHistory();

    /**
     * Get the most recent translation history.
     * @return a list of recent translates result.
     */
    List<String> getRecentTranslateHistory();

    /**
     * Delete the selected translation history.
     */
    void deleteTranslationHistory();

    /**
     * Delete all translation history.
     */
    void clearHistory();
    
    /**
     * Get a specific translation history.
     * @return a map from input text to translation output.
     */
    Map<String, String> getTransHistory();
}
