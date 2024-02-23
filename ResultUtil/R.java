package com.purehub.common.result;

import lombok.Data;

@Data
public class R<T> {

  private Integer code;
  private String message;
  private T data;

  public R(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> R<T> success() {
    return success(null);
  }

  public static <T> R<T> success(T data) {
    return build(RCodeEnum.SUCCESS, data);
  }

  public static <T> R<T> fail() {
    return fail(null);
  }

  public static <T> R<T> fail(T data) {
    return build(RCodeEnum.FAIL, data);
  }

  public static <T> R<T> custom(RCodeEnum code, T data) {
    return build(code, data);
  }

  private static <T> R<T> build(RCodeEnum codeEnum, T data) {
    return new R<>(codeEnum.getCode(), codeEnum.getMessage(), data);
  }

  public R<T> message(String message) {
    this.setMessage(message);
    return this;
  }

  public R<T> code(Integer code) {
    this.setCode(code);
    return this;
  }

  public R<T> data(T data) {
    this.setData(data);
    return this;
  }
}
