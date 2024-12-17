package org.example.mygraphqltest.global.resolver;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

import lombok.NonNull;
import org.example.mygraphqltest.domain.message.service.MessageService;
import org.example.mygraphqltest.global.exception.CustomGraphQLException;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class CustomExceptionResolver implements DataFetcherExceptionResolver {
    private final MessageService messageService;

    public CustomExceptionResolver(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @NonNull
    public Mono<List<GraphQLError>> resolveException(Throwable ex, DataFetchingEnvironment env) {
        return Mono.deferContextual(ctx -> {
            // Reactor Context에서 locale 가져오기
            Locale locale = ctx.getOrDefault("locale", Locale.getDefault());

            // 커스텀 예외 처리
            if (ex instanceof CustomGraphQLException customException) {
                return messageService.getMessage(locale.toString(), "error.user.not_found")
                        .map(message4 -> GraphqlErrorBuilder.newError()
                                .message(message4)
                                .errorType(customException.getErrorType())
                                .extensions(customException.getExtensions())
                                .location(env.getField().getSourceLocation())
                                .build()
                        )
                        .map(Collections::singletonList);
            }

            // 기본 예외 처리
            return Mono.just(Collections.singletonList(
                    GraphqlErrorBuilder.newError()
                            .message("Unexpected error occurred")
                            .location(env.getField().getSourceLocation())
                            .build()
            ));
        });
    }
}
//    @Override
//    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
//        if (ex instanceof CustomGraphQLException) {
//            CustomGraphQLException customException = (CustomGraphQLException) ex;
//
//            //임시로 정의한 locale, db에 있는 User not found. 가 message 에 저장되어야 합ㅂ니다.
//            String locale = "en_US";
//
//            // m = null
//            // message3 <- Mono<String> 이기 때문에 GraphqlErrorBuilder.message 로 넣을 수가 없습니다.
//            messageService.getMessage("en_US","error.user.not_found").doOnNext(msg -> System.out.println("Message : " + msg)).subscribe();
//
//            return messageService.getMessage("en_US", "error.user.not_found")
//                    .map(message4 -> GraphqlErrorBuilder.newError()
//                            .message(message4)
//                            .errorType(customException.getErrorType())
//                            .extensions(customException.getExtensions())
//                            .location(env.getField().getSourceLocation())
//                            .build()
//                    )
//                    .block();
//        }
//        else {
//            return null;
//        }
//    }
