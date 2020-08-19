package com.bitfs.client.exception;

public class BitfsApiException extends RuntimeException {


  public static final String RUNTIME_ERROR = "runtimeError";
  public static final String INPUT_ERROR = "inputError";
  public static final String KEY_MISSING = "keyMissing";
  public static final String SYS_ERROR = "sysError";
  public static final String ENV_ERROR = "environmentError";
  public static final String EXEC_ERROR = "executeError";
  public static final String ORDER_ERROR = "orderError";
  private final String errCode;

  public BitfsApiException(String errType, String errMsg) {
    super(errMsg);
    this.errCode = errType;
  }

  public BitfsApiException(String errType, String errMsg, Throwable e) {
    super(errMsg, e);
    this.errCode = errType;
  }

  public String getErrType() {
    return this.errCode;
  }
}
