package com.ssafy.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_FORBIDDEN(409, "이미 존재하는 사용자 ID 입니다"),
    USER_NOT_FOUND(404, "존재하지 않는 계정입니다"),
    USER_ENVALID_PASSWORD(401, "잘못된 비밀번호입니다");
    
    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
