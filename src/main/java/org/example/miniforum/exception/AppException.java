package org.example.miniforum.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private ErrorCode errcode;

    public AppException(ErrorCode errcode) {
        super(errcode.getMessage());
        this.errcode = errcode;
    }
}
