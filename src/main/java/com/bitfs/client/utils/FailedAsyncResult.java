package com.bitfs.client.utils;

import com.bitfs.client.AsyncResult;
import com.bitfs.client.exception.BitfsApiException;

public class FailedAsyncResult<T> implements AsyncResult<T> {

  private final BitfsApiException exception;

  public FailedAsyncResult(BitfsApiException exception) {
    this.exception = exception;
  }

  @Override
  public BitfsApiException getException() {
    return exception;
  }

  @Override
  public boolean succeeded() {
    return false;
  }

  @Override
  public T getData() {
    return null;
  }
}
