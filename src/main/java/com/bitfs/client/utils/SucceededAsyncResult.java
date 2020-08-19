package com.bitfs.client.utils;

import com.bitfs.client.AsyncResult;
import com.bitfs.client.exception.BitfsApiException;

public class SucceededAsyncResult<T> implements AsyncResult<T> {

  private final T data;

  public SucceededAsyncResult(T data) {
    this.data = data;
  }

  @Override
  public BitfsApiException getException() {
    return null;
  }

  @Override
  public boolean succeeded() {
    return true;
  }

  @Override
  public T getData() {
    return data;
  }
}
