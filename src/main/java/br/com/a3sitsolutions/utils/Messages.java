package br.com.a3sitsolutions.utils;

import br.com.a3sitsolutions.utils.translationsn.Translator;

public enum Messages {

    BUNDLE("http_messages"),
    SAVE_PROBLEM("save.problem"),
    LENGTH_PROBLEM("length.problem"),
    LENGTH_ROW_PROBLEM("length.row.problem"),
    NOT_FOUND("not.found"),
    NOT_FOUND_ID("not.found.id"),
    ID_CONVERTER_PROBLEM("id.converter.problem");

    private final String code;

    Messages(String code) {
        this.code = code;
    }

    public String getMessage() {
        return Translator.toLocale(Messages.BUNDLE.code, this.code);
    }

    public String getMessage(Object... args) {
        return Translator.toLocale(Messages.BUNDLE.code, this.code, args);
    }

    public static String formatLengthProblemMessage(int minLen, int maxLen) {
        return Messages.LENGTH_PROBLEM.getMessage(
                String.format("%dx%d", minLen, minLen),
                String.format("%dx%d", maxLen, maxLen)
        );
    }

    public static String formatRowLengthProblemMessage(int len) {
        return Messages.LENGTH_ROW_PROBLEM.getMessage(
                String.format("%d", len)
        );
    }
}
