package use_case.translator;

import entity.Translate;

/**
 * DAO for handling translation operations.
 */
public interface TranslatorUserDataAccessInterface {

    /**
     * Saves a translation request.
     * @param translation the translation request to save
     */
    void saveTranslation(Translate translation);

    /**
     * Retrieves a translation by its ID.
     * @param id the unique identifier of the translation
     * @return the translation request associated with the given ID
     */
    Translate getTranslationById(String id);

    /**
     * Deletes a translation by its ID.
     * @param id the unique identifier of the translation to delete
     */
    void deleteTranslationById(String id);

    /**
     * Retrieves all translation requests made by a specific user.
     * @param username the username of the user
     * @return a list of translations made by the user
     */
    List<Translate> getAllTranslationsByUser(String username);

    /**
     * Retrieves the most recent translation request for a specific user.
     * @param username the username of the user
     * @return the most recent translation request made by the user
     */
    Translate getMostRecentTranslation(String username);

    /**
     * Checks if a translation exists by its ID.
     * @param id the unique identifier of the translation
     * @return true if the translation exists; false otherwise
     */
    boolean existsById(String id);

    /**
     * Updates an existing translation request.
     * @param id the unique identifier of the translation to update
     * @param updatedTranslation the updated translation details
     */
    void updateTranslation(String id, Translate updatedTranslation);
}
