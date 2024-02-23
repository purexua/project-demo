package com.purehub.common.result;

public enum RCodeEnum {
    SUCCESS(200, "Success"),
    FAIL(500, "Internal Server Error");

    private final Integer code;
    private final String message;

    RCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
