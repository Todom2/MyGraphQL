package org.example.mygraphqltest.global.config;

import org.example.mygraphqltest.global.resolver.LocaleResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.server.i18n.LocaleContextResolver;

@Configuration
public class WebConfig extends DelegatingWebFluxConfiguration {

    @Override
    public LocaleContextResolver createLocaleContextResolver() {
        return new LocaleResolver();
    }
}