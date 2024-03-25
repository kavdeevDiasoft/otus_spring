package ru.diasoft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ApplicationConfig {

    private final String csvFileName;
    private final int minRightAnswer;
    private final Locale locale;


    public ApplicationConfig(@Value("${minrightanswer}") int minRightAnswer,
                             @Value("${filename}") String filename) {
        this.csvFileName = filename;
        this.minRightAnswer = minRightAnswer;
        this.locale = new Locale("en");
    }

    public String getCsvFileName() {
        return csvFileName;
    }

    public int getMinRightAnswer() {
        return minRightAnswer;
    }

    public Locale getLocale() {
        return locale;
    }

}