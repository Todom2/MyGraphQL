package org.example.mygraphqltest.global.exception;

public class BusinessException extends RuntimeException{
    private final String messageKey;

    public BusinessException(String messageKey){
        super(messageKey);
        this.messageKey = messageKey;
    }

    public String getMessageKey(){
        return messageKey;
    }

    // 중복 이름 예외
    public static class DuplicateUserNameException extends BusinessException {
        public DuplicateUserNameException(String messageKey) {
            super(messageKey);
        }
    }

    // 유저를 찾지 못했을 때 예외
    public static class UserNotFoundException extends BusinessException {
        public UserNotFoundException(String messageKey) {
            super(messageKey);
        }
    }
}
