package org.example.mygraphqltest.global.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public abstract class CustomGraphQLException extends RuntimeException implements GraphQLError {

    private final String errorCode;
    private final ErrorClassification errorType;

    public CustomGraphQLException(String errorCode, ErrorClassification errorType) {
        this.errorCode = errorCode != null ? errorCode : ""; // 기본값 처리
        this.errorType = errorType;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null; // 기본적으로 위치는 없음
    }

    @Override
    public ErrorClassification getErrorType() {
        return this.errorType;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return Map.of(
                "code", this.errorCode,
                "exception", this.getClass().getSimpleName()
        );
    }
}