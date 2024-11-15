package use_case.translator;

import entity.Translate;
import entity.User;
import com.deepl.api.*;


/**
 * The Translator Interactor.
 */

public class TranslatorInteractor implements TranslatorInputBoundary {
    private final Translator translatorService;
    private final TranslatorOutputBoundary translatorPresenter;

    public TranslatorInteractor(Translator translatorService,
                                TranslatorOutputBoundary translatorPresenter,) {
        this.translatorService = translatorService;
        this.translatorPresenter = translatorPresenter;
    }
    public void execute(TranslatorInputData inputData) {
        String inputText = inputData.getInput();
        String inputLanguage = inputData.getInputLanguage();
        String outputLanguage = inputData.getOutputLanguage();

        // not sure
        if (inputText == null || inputText.isBlank()) {
            translatorPresenter.prepareFailView("Input text cannot be empty.");
            return;

        // not sure
        if (inputLanguage.equals(outputLanguage)) {
            translatorPresenter.prepareFailView("Input and output languages must be different.");
            return;
        }

        try {

            String translatedText = Translate.translateText(inputText, inputLanguage, outputLanguage);

            TranslatorOutputData outputData = new TranslatorOutputData(translatedText);
            translatorPresenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            translatorPresenter.prepareFailView("An error occurred during translation: " + e.getMessage());
        }
    }
}
}