package com.bitfs.client;

import com.bitfs.client.impl.BitfsApiInternalFactory;
import com.bitfs.client.model.*;
import com.bitfs.client.request.GetAccountRequest;
import com.bitfs.client.request.NewOrderRequest;
import com.bitfs.client.request.QueryOrderRequest;
import com.bitfs.client.request.WithdrawRequest;

import java.util.List;

/**
 * Asynchronous request interface, invoking BITFS RestAPI via asynchronous method. All methods in this interface will return immediately, do not wait
 * the server's response. So you must implement the ResponseCallback interface yourself. As long as the server response received, the onResponse
 * callback method will be called..
 */
public interface AsyncRequestClient {

    static AsyncRequestClient create(String appKey, String secretKey,String mchKey) {
        return BitfsApiInternalFactory.getInstance().createAsyncRequestClient(
                appKey, secretKey,mchKey, new RequestOptions());
    }

    /**
     * 请求充值接口
     * @param request
     * @return
     */
    void createOrderByCurrency(NewOrderRequest request,ResponseCallback<AsyncResult<CreateOrderResult>> callback);
    /**
     * 请求充值接口
     * @param request
     * @return
     */
    void createOrderByCoin(NewOrderRequest request,ResponseCallback<AsyncResult<CreateOrderResult>> callback);

    void getCurrencyList(ResponseCallback<AsyncResult<List<CoinResult>>> callback);

    void getCoinList(ResponseCallback<AsyncResult<List<CoinResult>>> callback);

    void withdrawByCurrency(WithdrawRequest request,ResponseCallback<AsyncResult<WithdrawResult>> callback);

    void withdrawByCoin(WithdrawRequest request,ResponseCallback<AsyncResult<WithdrawResult>> callback);

    void getAccount(GetAccountRequest request,ResponseCallback<AsyncResult<Account>> callback);

    void queryOrder(QueryOrderRequest request,ResponseCallback<AsyncResult<QueryOrderResult>> callback);

}



