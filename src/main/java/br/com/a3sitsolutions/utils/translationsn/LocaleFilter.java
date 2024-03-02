package br.com.a3sitsolutions.utils.translationsn;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import java.util.Locale;

@Provider
public class LocaleFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String acceptLanguage = requestContext.getHeaderString(HttpHeaders.ACCEPT_LANGUAGE);
        if (acceptLanguage != null) {
            Locale locale = Locale.forLanguageTag(acceptLanguage);
            LocaleThreadLocal.set(locale);
        } else {
            LocaleThreadLocal.set(Locale.ENGLISH);
        }
    }

}
