package use_case.translator;

/**
 * The input data for the Translator Use case.
 */
public class TranslatorInputData {
    private final String input;
    private String inputLanguage;

    public TranslatorInputData(String input) {
        this.input = input;
    }

    public TranslatorInputData(String input, String inputLanguage) {
        this.input = input;
        this.inputLanguage = inputLanguage;
    }

    public String getInput() {
        return input;
    }

    public String getInputLanguage() {
        return inputLanguage;
    }
}
