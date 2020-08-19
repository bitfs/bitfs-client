package com.bitfs.client.impl;

import com.bitfs.client.request.GetAccountRequest;
import com.bitfs.client.request.NewOrderRequest;
import com.bitfs.client.request.QueryOrderRequest;
import com.bitfs.client.SyncRequestClient;
import com.bitfs.client.model.*;
import com.bitfs.client.request.WithdrawRequest;

import java.util.List;


public class SyncRequestImpl implements SyncRequestClient {

  private final RestApiRequestImpl requestImpl;

  SyncRequestImpl(RestApiRequestImpl requestImpl) {
    this.requestImpl = requestImpl;
  }

  @Override
  public CreateOrderResult createOrderByCurrency(NewOrderRequest request) {
    return RestApiInvoker.callSync(requestImpl.createOrderByCurrency(request));
  }

  @Override
  public CreateOrderResult createOrderByCoin(NewOrderRequest request) {
    return RestApiInvoker.callSync(requestImpl.createOrderByCoin(request));
  }

  @Override
  public List<CoinResult> getCurrencyList() {
    return RestApiInvoker.callSync(requestImpl.getCurrencyList());
  }

  @Override
  public List<CoinResult> getCoinList() {
    return RestApiInvoker.callSync(requestImpl.getCoinList());
  }

  @Override
  public WithdrawResult withdrawByCurrency(WithdrawRequest request) {
    return RestApiInvoker.callSync(requestImpl.withdrawByCurrency(request));
  }

  @Override
  public WithdrawResult withdrawByCoin(WithdrawRequest request) {
    return RestApiInvoker.callSync(requestImpl.withdrawByCoin(request));
  }

  @Override
  public Account getAccount(GetAccountRequest request) {
    return RestApiInvoker.callSync(requestImpl.getAccount(request));
  }

  @Override
  public QueryOrderResult queryOrder(QueryOrderRequest request) {
    return RestApiInvoker.callSync(requestImpl.queryOrder(request));
  }
}
