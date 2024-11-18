package org.example.miniforum.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED(9999,"uncategorized", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001,"user existed", HttpStatus.BAD_REQUEST),
    INVALID_KEY(1002,"invalid key", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003,"username must have 2 characterss", HttpStatus.BAD_REQUEST),
    LASTNAME_INVALID(1004,"last name must not null", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1005,"user not exist", HttpStatus.NOT_FOUND),
    PASSWORD_INCORRECT(1010,"password incorrect", HttpStatus.BAD_REQUEST),
    UN_AUTHENTICATED(1006,"unauthenticated", HttpStatus.UNAUTHORIZED),
    UN_AUTHORIZED(1007,"do not have permission", HttpStatus.FORBIDDEN),;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }
    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
