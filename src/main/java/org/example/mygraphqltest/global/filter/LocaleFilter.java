package org.example.mygraphqltest.global.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Locale;

@Component
public class LocaleFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String acceptLanguage = exchange.getRequest().getHeaders().getFirst(HttpHeaders.ACCEPT_LANGUAGE);
        Locale locale = (acceptLanguage != null) ? Locale.forLanguageTag(acceptLanguage) : Locale.getDefault();

        return chain.filter(exchange)
                .contextWrite(ctx -> ctx.put("locale", locale));
    }
}