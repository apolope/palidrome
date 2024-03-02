package br.com.a3sitsolutions.utils.translationsn;

import br.com.a3sitsolutions.utils.translationsn.LocaleThreadLocal;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {
    public static String toLocale(String bundle, String msgCode) {
        ResourceBundle messages = ResourceBundle.getBundle(bundle, LocaleThreadLocal.get());
        return messages.getString(msgCode);
    }

    public static String toLocale(String bundle,String msgCode, Object[] args) {
        ResourceBundle messages = ResourceBundle.getBundle(bundle, LocaleThreadLocal.get());
        String  msgValue = messages.getString(msgCode);
        MessageFormat messageFormat = new MessageFormat(msgValue);
        return messageFormat.format(args);
    }

    public static Locale currentLocale() {
        return LocaleThreadLocal.get();
    }
}
