package org.example.mygraphqltest.global.resolver;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.example.mygraphqltest.domain.message.service.MessageService;
import org.example.mygraphqltest.global.exception.BusinessException;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.Nullable;


@Slf4j
@Configuration
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {
    private final MessageService messageService;

    public GraphQLExceptionResolver(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    @Nullable
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        log.debug(">>> resolveToSingleError called with exception: {}", ex.getClass().getName());
        if (ex instanceof BusinessException be) {
            String locale = "en_US"; // 실제 구현 시 LocaleWebFilter나 Context 사용
            // DB에서 locale+code에 맞는 메시지를 동기적으로 가져오기 위해 block() 호출
            String message = messageService.getMessage(locale, be.getMessageKey()).block();
            log.debug("message = {}", message);
            return GraphqlErrorBuilder.newError()
                    .errorType(ErrorType.NOT_FOUND)
                    .message(ex.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        }
        return null;
    }
}
