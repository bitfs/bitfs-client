package com.bitfs.client.impl;

import com.alibaba.fastjson.JSONObject;
import com.bitfs.client.model.*;
import com.bitfs.client.request.GetAccountRequest;
import com.bitfs.client.request.NewOrderRequest;
import com.bitfs.client.exception.BitfsApiException;
import com.bitfs.client.request.QueryOrderRequest;
import com.bitfs.client.request.WithdrawRequest;
import com.bitfs.client.utils.JsonWrapper;
import com.bitfs.client.utils.JsonWrapperArray;
import com.bitfs.client.utils.UrlParamsBuilder;

import okhttp3.Request;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class RestApiRequestImpl {

  private String apiKey;
  private String secretKey;
  private String mchKey;
  private String tradeUrl = "";
  private RequestOptions options;
  private String tradingHost;

  RestApiRequestImpl(String apiKey, String secretKey, String mchKey,RequestOptions options) {
    this.apiKey = apiKey;
    this.secretKey = secretKey;
    this.mchKey=mchKey;
    this.options = options;
    try {
      String host = new URL(this.options.getUrl()).getHost();
      this.tradingHost = host;
      this.tradeUrl = this.options.getUrl();
    } catch (Exception e) {
    }
  }

  private Request createRequestByGet(String address, UrlParamsBuilder builder) {
    return createRequestByGet(tradeUrl, address, builder);
  }

  private Request createRequestByGet(String url, String address, UrlParamsBuilder builder) {
    return createRequest(url, address, builder);
  }

  private Request createRequest(String url, String address, UrlParamsBuilder builder) {
    String requestUrl = url + address;
    if (builder != null) {
      if (builder.hasPostParam()) {
        return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
            .addHeader("Content-Type", "application/json").build();
      } else {
        return new Request.Builder().url(requestUrl + builder.buildUrl())
            .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
      }
    } else {
      return new Request.Builder().url(requestUrl)
          .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
    }
  }

  private Request createRequestWithSignature(String url, String address,
                                             String host,
                                             UrlParamsBuilder builder) {
    if (builder == null) {
      throw new BitfsApiException(BitfsApiException.RUNTIME_ERROR,
          "[Invoking] Builder is null when create request with Signature");
    }
    String requestUrl = url + address;
    if (builder.hasPostParam()) {
      new ApiSign().createSignature(apiKey, secretKey,mchKey,builder);
      requestUrl += builder.buildUrl();
      return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
          .addHeader("Content-Type", "application/json").build();
    } else {
      new ApiSign().createSignature(apiKey, secretKey, mchKey,builder);
      requestUrl += builder.buildUrl();
      return new Request.Builder().url(requestUrl)
          .addHeader("Content-Type", "application/x-www-form-urlencoded").build();
    }
  }

  private Request createRequestByPostWithSignature(String address, UrlParamsBuilder builder) {
    return createRequestWithSignature(tradeUrl, address, tradingHost, builder.setPostMode(true));
  }

  private Request createRequestByGetWithSignature(String address, UrlParamsBuilder builder) {
    return createRequestWithSignature(tradeUrl, address, tradingHost, builder);
  }

  RestApiRequest<List<CoinResult>> getCurrencyList() {
    RestApiRequest<List<CoinResult>> request = new RestApiRequest<>();
    UrlParamsBuilder builder = UrlParamsBuilder.build();
    request.request = createRequestByGet("/pay/v1/currencyList", builder);
    request.jsonParser = (json -> {
      JsonWrapperArray data = json.getJsonArray("result");
      List<CoinResult> results=new ArrayList<>();
      data.forEach(result->{
        CoinResult coinResult=new CoinResult();
        coinResult.setFee_name(result.getStringOrDefault("fee_name",null));
        coinResult.setFee_type(result.getStringOrDefault("fee_type",null));
        results.add(coinResult);
      });
      return results;
    });
    return request;
  }

  RestApiRequest<List<CoinResult>> getCoinList() {
    RestApiRequest<List<CoinResult>> request = new RestApiRequest<>();
    UrlParamsBuilder builder = UrlParamsBuilder.build();
    request.request = createRequestByGet("/pay/v1/digiccyList", builder);
    request.jsonParser = (json -> {
      JsonWrapperArray data = json.getJsonArray("result");
      List<CoinResult> results=new ArrayList<>();
      data.forEach(result->{
        CoinResult coinResult=new CoinResult();
        coinResult.setFee_name(result.getStringOrDefault("fee_name",null));
        coinResult.setFee_type(result.getStringOrDefault("fee_type",null));
        results.add(coinResult);
      });
      return results;
    });
    return request;
  }

  RestApiRequest<CreateOrderResult> createOrderByCurrency(NewOrderRequest newOrderRequest) {
    InputChecker.checker().shouldNotNull(newOrderRequest.getBody(), "body")
            .shouldNotNull(newOrderRequest.getDevice_type(), "device_type")
            .shouldNotNull(newOrderRequest.getLocal(), "local")
            .shouldNotNull(newOrderRequest.getFee_type(), "fee_type")
            .shouldNotNull(newOrderRequest.getTotal_amount(), "total_amount")
            .shouldNotNull(newOrderRequest.getTrade_type(), "trade_type")
            .shouldNotNull(newOrderRequest.getNonce_str(), "nonce_str")
            .shouldNotNull(newOrderRequest.getNotify_url(), "notify_url")
            .shouldNotNull(newOrderRequest.getOut_trade_no(), "out_trade_no")
            .shouldNotNull(newOrderRequest.getSpbill_create_ip(), "spbill_create_ip");

    Account account = AccountInfo.getAccount(apiKey);
    if (account == null) {
      throw new BitfsApiException(BitfsApiException.INPUT_ERROR, "[Input] No such account");
    }
    RestApiRequest<CreateOrderResult> request = new RestApiRequest<>();

    UrlParamsBuilder builder = UrlParamsBuilder.build()
            .putToPost("body", newOrderRequest.getBody())
            .putToPost("device_type", newOrderRequest.getDevice_type())
            .putToPost("local", newOrderRequest.getLocal())
            .putToPost("fee_type", newOrderRequest.getFee_type())
            .putToPost("total_amount", newOrderRequest.getTotal_amount())
            .putToPost("trade_type", newOrderRequest.getTrade_type())
            .putToPost("nonce_str", newOrderRequest.getNonce_str())
            .putToPost("notify_url", newOrderRequest.getNotify_url())
            .putToPost("spbill_create_ip", newOrderRequest.getSpbill_create_ip())
            .putToPost("timestamp", newOrderRequest.getTimestamp())
            .putToPost("out_trade_no", newOrderRequest.getOut_trade_no());

    request.request = createRequestByPostWithSignature("/pay/v1/unifiedorder", builder);
    request.jsonParser = (jsonWrapper -> {
      JsonWrapper data = jsonWrapper.getJsonObject("result");
      CreateOrderResult result=new CreateOrderResult();
      result.setMch_id(data.getString("mch_id"));
      result.setTrade_type(data.getString("trade_type"));
      result.setPay_url(data.getString("pay_url"));
      result.setSign(data.getString("sign"));
      result.setNonceStr(data.getString("nonce_str"));
      result.setTransaction_id(data.getString("transaction_id"));
      return result;
    });
    return request;
  }

  RestApiRequest<CreateOrderResult> createOrderByCoin(NewOrderRequest newOrderRequest) {
    InputChecker.checker().shouldNotNull(newOrderRequest.getBody(), "body")
            .shouldNotNull(newOrderRequest.getDevice_type(), "device_type")
            .shouldNotNull(newOrderRequest.getLocal(), "local")
            .shouldNotNull(newOrderRequest.getFee_type(), "fee_type")
            .shouldNotNull(newOrderRequest.getTotal_amount(), "total_amount")
            .shouldNotNull(newOrderRequest.getTrade_type(), "trade_type")
            .shouldNotNull(newOrderRequest.getNonce_str(), "nonce_str")
            .shouldNotNull(newOrderRequest.getNotify_url(), "notify_url")
            .shouldNotNull(newOrderRequest.getOut_trade_no(), "out_trade_no")
            .shouldNotNull(newOrderRequest.getSpbill_create_ip(), "spbill_create_ip");

    Account account = AccountInfo.getAccount(apiKey);
    if (account == null) {
      throw new BitfsApiException(BitfsApiException.INPUT_ERROR, "[Input] No such account");
    }
    RestApiRequest<CreateOrderResult> request = new RestApiRequest<>();

    UrlParamsBuilder builder = UrlParamsBuilder.build()
            .putToPost("body", newOrderRequest.getBody())
            .putToPost("device_type", newOrderRequest.getDevice_type())
            .putToPost("local", newOrderRequest.getLocal())
            .putToPost("fee_type", newOrderRequest.getFee_type())
            .putToPost("total_amount", newOrderRequest.getTotal_amount())
            .putToPost("trade_type", newOrderRequest.getTrade_type())
            .putToPost("nonce_str", newOrderRequest.getNonce_str())
            .putToPost("notify_url", newOrderRequest.getNotify_url())
            .putToPost("spbill_create_ip", newOrderRequest.getSpbill_create_ip())
            .putToPost("timestamp", newOrderRequest.getTimestamp())
            .putToPost("out_trade_no", newOrderRequest.getOut_trade_no());

    request.request = createRequestByPostWithSignature("/pay/v1/coinunifiedorder", builder);
    request.jsonParser = (jsonWrapper -> {
      JsonWrapper data = jsonWrapper.getJsonObject("result");
      CreateOrderResult result=new CreateOrderResult();
      result.setMch_id(data.getString("mch_id"));
      result.setTrade_type(data.getString("trade_type"));
      result.setPay_url(data.getString("pay_url"));
      result.setSign(data.getString("sign"));
      result.setNonceStr(data.getString("nonce_str"));
      result.setTransaction_id(data.getString("transaction_id"));
      return result;
    });
    return request;
  }

  RestApiRequest<Account> getAccount(GetAccountRequest getAccountRequest) {
    InputChecker.checker().shouldNotNull(getAccountRequest.getAccount_type(), "fee_type")
            .shouldNotNull(getAccountRequest.getNonce_str(), "nonce_str");
    RestApiRequest<Account> request = new RestApiRequest<>();
    UrlParamsBuilder builder = UrlParamsBuilder.build()
            .putToPost("fee_type", getAccountRequest.getAccount_type())
            .putToPost("nonce_str", getAccountRequest.getNonce_str());
    request.request = createRequestByPostWithSignature("/pay/v1/checkacct", builder);
    request.jsonParser = (jsonWrapper -> {
      JsonWrapper data = jsonWrapper.getJsonObject("result");
      Account result=new Account();
      result.setMch_id(data.getString("mch_id"));
      result.setAccountType(data.getString("account_type"));
      result.setBalance(data.getBigDecimal("balance"));
      result.setNonce_str(data.getString("nonce_str"));
      result.setFreeze(data.getBigDecimal("freeze"));
      result.setSign(data.getString("sign"));
      return result;
    });
    return request;
  }

  /**
   * 法币提币接口
   * @param withdrawRequest
   * @return
   */
  RestApiRequest<WithdrawResult> withdrawByCurrency(WithdrawRequest withdrawRequest) {
    InputChecker.checker().shouldNotNull(withdrawRequest.getTimestamp(), "timestamp")
            .shouldNotNull(withdrawRequest.getArea_code(), "area_code")
            .shouldNotNull(withdrawRequest.getGet_account(), "get_account")
            .shouldNotNull(withdrawRequest.getFee_type(), "fee_type")
            .shouldNotNull(withdrawRequest.getTotal_amount(), "total_amount")
            .shouldNotNull(withdrawRequest.getTrade_type(), "trade_type")
            .shouldNotNull(withdrawRequest.getNonce_str(), "nonce_str")
            .shouldNotNull(withdrawRequest.getNotify_url(), "notify_url")
            .shouldNotNull(withdrawRequest.getOut_trade_no(), "out_trade_no")
            .shouldNotNull(withdrawRequest.getSpbill_create_ip(), "spbill_create_ip");

    Account account = AccountInfo.getAccount(apiKey);
    if (account == null) {
      throw new BitfsApiException(BitfsApiException.INPUT_ERROR, "[Input] No such account");
    }
    RestApiRequest<WithdrawResult> request = new RestApiRequest<>();

    UrlParamsBuilder builder = UrlParamsBuilder.build()
            .putToPost("area_code", withdrawRequest.getArea_code())
            .putToPost("get_account", withdrawRequest.getGet_account())
            .putToPost("fee_type", withdrawRequest.getFee_type())
            .putToPost("total_amount", withdrawRequest.getTotal_amount())
            .putToPost("trade_type", withdrawRequest.getTrade_type())
            .putToPost("nonce_str", withdrawRequest.getNonce_str())
            .putToPost("notify_url", withdrawRequest.getNotify_url())
            .putToPost("spbill_create_ip", withdrawRequest.getSpbill_create_ip())
            .putToPost("timestamp", withdrawRequest.getTimestamp())
            .putToPost("out_trade_no", withdrawRequest.getOut_trade_no());

    request.request = createRequestByPostWithSignature("/pay/v1/withdraw", builder);
    request.jsonParser = (jsonWrapper -> {
      JsonWrapper data = jsonWrapper.getJsonObject("result");
      WithdrawResult result=new WithdrawResult();
      result.setMch_id(data.getString("mch_id"));
      result.setTrade_type(data.getString("trade_type"));
      result.setCustomer_id(data.getString("customer_id"));
      result.setSign(data.getString("sign"));
      result.setNonce_str(data.getString("nonce_str"));
      result.setTransaction_id(data.getString("transaction_id"));
      return result;
    });
    return request;
  }

  /**
   * 数字货币提币接口
   * @param withdrawRequest
   * @return
   */
  RestApiRequest<WithdrawResult> withdrawByCoin(WithdrawRequest withdrawRequest) {
    InputChecker.checker().shouldNotNull(withdrawRequest.getTimestamp(), "timestamp")
            .shouldNotNull(withdrawRequest.getArea_code(), "area_code")
            .shouldNotNull(withdrawRequest.getGet_account(), "get_account")
            .shouldNotNull(withdrawRequest.getFee_type(), "fee_type")
            .shouldNotNull(withdrawRequest.getTotal_amount(), "total_amount")
            .shouldNotNull(withdrawRequest.getTrade_type(), "trade_type")
            .shouldNotNull(withdrawRequest.getNonce_str(), "nonce_str")
            .shouldNotNull(withdrawRequest.getNotify_url(), "notify_url")
            .shouldNotNull(withdrawRequest.getOut_trade_no(), "out_trade_no")
            .shouldNotNull(withdrawRequest.getSpbill_create_ip(), "spbill_create_ip");

    Account account = AccountInfo.getAccount(apiKey);
    if (account == null) {
      throw new BitfsApiException(BitfsApiException.INPUT_ERROR, "[Input] No such account");
    }
    RestApiRequest<WithdrawResult> request = new RestApiRequest<>();

    UrlParamsBuilder builder = UrlParamsBuilder.build()
            .putToPost("area_code", withdrawRequest.getArea_code())
            .putToPost("get_account", withdrawRequest.getGet_account())
            .putToPost("fee_type", withdrawRequest.getFee_type())
            .putToPost("total_amount", withdrawRequest.getTotal_amount())
            .putToPost("trade_type", withdrawRequest.getTrade_type())
            .putToPost("nonce_str", withdrawRequest.getNonce_str())
            .putToPost("notify_url", withdrawRequest.getNotify_url())
            .putToPost("spbill_create_ip", withdrawRequest.getSpbill_create_ip())
            .putToPost("timestamp", withdrawRequest.getTimestamp())
            .putToPost("out_trade_no", withdrawRequest.getOut_trade_no());

    request.request = createRequestByPostWithSignature("/pay/v1/coinwithdraw", builder);
    request.jsonParser = (jsonWrapper -> {
      JsonWrapper data = jsonWrapper.getJsonObject("result");
      WithdrawResult result=new WithdrawResult();
      result.setMch_id(data.getString("mch_id"));
      result.setTrade_type(data.getString("trade_type"));
      result.setCustomer_id(data.getString("customer_id"));
      result.setSign(data.getString("sign"));
      result.setNonce_str(data.getString("nonce_str"));
      result.setTransaction_id(data.getString("transaction_id"));
      return result;
    });
    return request;
  }


  RestApiRequest<QueryOrderResult> queryOrder(QueryOrderRequest queryOrderRequest) {
    InputChecker.checker().shouldNotNull(queryOrderRequest.getOut_trade_no(), "out_trade_no")
            .shouldNotNull(queryOrderRequest.getNonce_str(), "nonce_str");

    RestApiRequest<QueryOrderResult> request = new RestApiRequest<>();
    UrlParamsBuilder builder = UrlParamsBuilder.build()
            .putToPost("out_trade_no", queryOrderRequest.getOut_trade_no())
            .putToPost("nonce_str", queryOrderRequest.getNonce_str());
    request.request = createRequestByPostWithSignature("/pay/v1/queryorder", builder);
    request.jsonParser = (jsonWrapper -> {
      JsonWrapper data = jsonWrapper.getJsonObject("result");
      QueryOrderResult result=new QueryOrderResult();
      result.setMch_id(data.getString("mch_id"));
      result.setAttach(data.getStringOrDefault("attach",null));
      result.setCustomer_id(data.getStringOrDefault("customer",null));
      result.setNonce_str(data.getString("nonce_str"));
      result.setOrder_fee(data.getStringOrDefault("order_fee",null));
      result.setFee_type(data.getStringOrDefault("fee_type",null));
      result.setTotal_amount(data.getStringOrDefault("total_amount",null));
      result.setOut_trade_no(data.getStringOrDefault("out_trade_no",null));
      result.setTick_price(data.getStringOrDefault("tick_price",null));
      result.setTrade_state(data.getStringOrDefault("trade_state",null));
      result.setTrade_state_desc(data.getStringOrDefault("trade_state_desc",null));
      result.setTrade_type(data.getStringOrDefault("trade_type",null));
      result.setTransaction_id(data.getStringOrDefault("transaction_id",null));
      result.setSign(data.getString("sign"));
      return result;
    });
    return request;
  }
}
