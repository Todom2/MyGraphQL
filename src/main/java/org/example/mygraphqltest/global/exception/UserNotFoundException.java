package org.example.mygraphqltest.global.exception;

import org.springframework.graphql.execution.ErrorType;

public class UserNotFoundException extends CustomGraphQLException{

    private static final String DEFAULT_ERROR_CODE = "error.user.not_found";
    //private static final String DEFAULT_MESSAGE = "사용자를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(DEFAULT_ERROR_CODE, ErrorType.BAD_REQUEST);
    }

    public UserNotFoundException(String errorCode) {
        super(errorCode, ErrorType.BAD_REQUEST);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
