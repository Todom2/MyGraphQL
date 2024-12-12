package org.example.mygraphqltest.global.resolver;

import io.micrometer.common.util.StringUtils;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;

import java.util.Locale;

public class LocaleResolver implements LocaleContextResolver {
    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        String language = exchange.getRequest().getHeaders().getFirst("Accept-Language");

        return new SimpleLocaleContext(StringUtils.isNotEmpty(language) ? Locale.forLanguageTag(language) : Locale.getDefault());
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        throw new UnsupportedOperationException("Not Supported");
    }
}
