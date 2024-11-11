package entity;

import com.deepl.api.*;

public class Translate {

    private final String inputLanguage;
    private final String outputLanguage;
    private final String inputText;

    public Translate(String inputLanguage, String outputLanguage, String inputText) {
        this.inputLanguage = inputLanguage;
        this.outputLanguage = outputLanguage;
        this.inputText = inputText;
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

    public String translateText() {
        String authKey = "563c8dbf-539b-48c2-bc82-fae6a6622677:fx";
        Translator translator = new Translator(authKey);
        TextResult result = translator.translateText(inputText, inputLanguage, outputLanguage);
        return result.getText();
    }
}
