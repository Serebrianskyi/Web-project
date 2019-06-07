package com.company.i18n;

import java.util.Locale;

public enum SupportedLocale {
    UA (new Locale("uk", "UA"), "ua"),
    EN (new Locale("en", "EN"), "en"),
    RU (new Locale("ru", "RU"), "ru");

    private final Locale locale;
    private final String param;
    private static final SupportedLocale DEFAULT_LOCALE = EN;

    SupportedLocale(Locale locale, String param) {
        this.locale = locale;
        this.param = param;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getParam() {
        return param;
    }

    public static Locale getDefault() {
        return DEFAULT_LOCALE.getLocale();
    }
}
