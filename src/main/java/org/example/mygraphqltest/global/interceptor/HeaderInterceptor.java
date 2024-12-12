//package org.example.mygraphqltest.global.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.graphql.server.WebGraphQlInterceptor;
//import org.springframework.graphql.server.WebGraphQlRequest;
//import org.springframework.graphql.server.WebGraphQlResponse;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//@Component
//public class HeaderInterceptor implements WebGraphQlInterceptor {
//    @Override
//    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain){
//        String locale = request.getHeaders().getFirst("locale");
//        log.debug("WebGraphQL Interceptor");
//        log.debug("Locale: " + locale);
//        return chain.next(request);
//    }
//}
