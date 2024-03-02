package br.com.a3sitsolutions.utils.translationsn;

import java.util.Locale;

public class LocaleThreadLocal {
    public static final ThreadLocal<Locale> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(Locale locale) {
        THREAD_LOCAL.set(locale);
    }

    public static Locale get() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
