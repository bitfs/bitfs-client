package com.bitfs.client;

import com.bitfs.client.model.*;
import com.bitfs.client.request.GetAccountRequest;
import com.bitfs.client.request.NewOrderRequest;
import com.bitfs.client.request.QueryOrderRequest;
import com.bitfs.client.impl.BitfsApiInternalFactory;
import com.bitfs.client.request.WithdrawRequest;

import java.util.List;


/**
 * Synchronous request interface, invoking BITFS RestAPI via synchronous method.<br> All methods in
 * this interface will be blocked until the RestAPI response.
 * <p>
 * If the invoking failed or timeout, the  will
 * be thrown.
 */
public interface SyncRequestClient {


    static SyncRequestClient create(String appKey, String secretKey,String mchKey) {
        return BitfsApiInternalFactory.getInstance().createSyncRequestClient(
                appKey, secretKey,mchKey, new RequestOptions());
    }

    static SyncRequestClient create(String apiKey, String secretKey,String mchKey, RequestOptions options) {
        return BitfsApiInternalFactory.getInstance().createSyncRequestClient(
                apiKey, secretKey,mchKey, options);
    }

    /**
     * 请求充值接口
     * @param request
     * @return
     */
    CreateOrderResult createOrderByCurrency(NewOrderRequest request);
    /**
     * 请求充值接口
     * @param request
     * @return
     */
    CreateOrderResult createOrderByCoin(NewOrderRequest request);

    List<CoinResult> getCurrencyList();

    List<CoinResult> getCoinList();

    WithdrawResult withdrawByCurrency(WithdrawRequest request);

    WithdrawResult withdrawByCoin(WithdrawRequest request);

    Account getAccount(GetAccountRequest request);

    QueryOrderResult queryOrder(QueryOrderRequest request);

}
