package com.bitfs.client.impl;


import com.bitfs.client.AsyncRequestClient;
import com.bitfs.client.AsyncResult;
import com.bitfs.client.model.*;
import com.bitfs.client.request.GetAccountRequest;
import com.bitfs.client.request.NewOrderRequest;
import com.bitfs.client.ResponseCallback;
import com.bitfs.client.request.QueryOrderRequest;
import com.bitfs.client.request.WithdrawRequest;

import java.util.List;

public class AsyncRequestImpl implements AsyncRequestClient {

  private final RestApiRequestImpl requestImpl;

  AsyncRequestImpl(RestApiRequestImpl requestImpl) {
    this.requestImpl = requestImpl;
  }


  @Override
  public void createOrderByCurrency(NewOrderRequest request, ResponseCallback<AsyncResult<CreateOrderResult>> callback) {
    RestApiInvoker.callASync(requestImpl.createOrderByCurrency(request),callback);
  }

  @Override
  public void createOrderByCoin(NewOrderRequest request, ResponseCallback<AsyncResult<CreateOrderResult>> callback) {
    RestApiInvoker.callASync(requestImpl.createOrderByCoin(request),callback);
  }

  @Override
  public void getCurrencyList(ResponseCallback<AsyncResult<List<CoinResult>>> callback) {
    RestApiInvoker.callASync(requestImpl.getCurrencyList(),callback);
  }

  @Override
  public void getCoinList(ResponseCallback<AsyncResult<List<CoinResult>>> callback) {
    RestApiInvoker.callASync(requestImpl.getCoinList(),callback);
  }

  @Override
  public void withdrawByCurrency(WithdrawRequest request, ResponseCallback<AsyncResult<WithdrawResult>> callback) {
    RestApiInvoker.callASync(requestImpl.withdrawByCurrency(request),callback);
  }

  @Override
  public void withdrawByCoin(WithdrawRequest request, ResponseCallback<AsyncResult<WithdrawResult>> callback) {
    RestApiInvoker.callASync(requestImpl.withdrawByCoin(request),callback);
  }

  @Override
  public void getAccount(GetAccountRequest request, ResponseCallback<AsyncResult<Account>> callback) {
    RestApiInvoker.callASync(requestImpl.getAccount(request),callback);
  }

  @Override
  public void queryOrder(QueryOrderRequest request, ResponseCallback<AsyncResult<QueryOrderResult>> callback) {
    RestApiInvoker.callASync(requestImpl.queryOrder(request),callback);
  }
}
