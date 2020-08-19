package com.bitfs.client.impl;

import com.bitfs.client.*;
import com.bitfs.client.model.RequestOptions;

import java.net.URI;

public final class BitfsApiInternalFactory {

  private static final BitfsApiInternalFactory instance = new BitfsApiInternalFactory();

  public static BitfsApiInternalFactory getInstance() {
    return instance;
  }

  private BitfsApiInternalFactory() {
  }

  public SyncRequestClient createSyncRequestClient(
      String apiKey, String secretKey,String mchKey, RequestOptions options) {
    RequestOptions requestOptions = new RequestOptions(options);
    RestApiRequestImpl requestImpl = new RestApiRequestImpl(apiKey, secretKey, mchKey,requestOptions);
    if (!"".equals(apiKey) && !"".equals(secretKey)) {
      AccountInfo.setAccount(apiKey, requestImpl);
    }
    return new SyncRequestImpl(requestImpl);
  }

  public AsyncRequestClient createAsyncRequestClient(
      String apiKey, String secretKey,String mchKey, RequestOptions options) {
    RequestOptions requestOptions = new RequestOptions(options);
    RestApiRequestImpl requestImpl = new RestApiRequestImpl(apiKey, secretKey,mchKey, requestOptions);
    if (!"".equals(apiKey) && !"".equals(secretKey)) {
      AccountInfo.setAccount(apiKey, requestImpl);
    }
    return new AsyncRequestImpl(requestImpl);
  }

}
