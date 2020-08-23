package com.bitfs.demo.controller;

import com.bitfs.client.exception.BitfsApiException;
import com.bitfs.client.impl.CoinInfo;
import com.bitfs.client.model.*;
import com.bitfs.client.request.*;
import com.bitfs.client.SyncRequestClient;
import com.bitfs.client.constants.PayConstant;
import com.bitfs.client.utils.PaySign;
import com.bitfs.config.PayConfigProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

@RestController
@RequestMapping("/demo")
public class ClientController {

    @Autowired
    private PayConfigProperties payInfoProperties;
    @Autowired
    private PaySign paySign;

    /**
     * 查询BITFS支持的法币
     * @return
     */
    @GetMapping("currencyList")
    public List<CoinResult> currencyList(){
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                "", "","",options);
        List<CoinResult> results=syncRequestClient.getCurrencyList();
        return results;

    }

    /**
     * 查询BITFS支持的数字货币
     * @return
     */
    @GetMapping("coinList")
    public List<CoinResult> coinList(){
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                "", "","",options);
        List<CoinResult> results=syncRequestClient.getCoinList();
        return results;

    }

    /**
     * 法币充币请求接口
     * @return
     */
    @PostMapping("createOrderByCurrency")
    public CommonResponse createOrderByCurrency(@RequestBody WebTestRequest request) throws Exception {
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        CommonResponse response=new CommonResponse();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                payInfoProperties.getAppKey(), payInfoProperties.getSecretKey(),payInfoProperties.getMchKey(),options);

        String orderId=UUID.randomUUID().toString().replace("-","");
        String ip="127.0.0.1";
        String deviceType="pc";
        NewOrderRequest orderRequest=new NewOrderRequest(
                deviceType,
                UUID.randomUUID().toString().replace("-",""),
                orderId,
                ip,
//                PayConstant.CURRENCY_TYPE,
                request.getCoinName(),
                PayConstant.TRADE_TYPE,
                PayConstant.COMMODITY_NAME,
//                BigDecimal.valueOf(100),
                request.getNum(),
                payInfoProperties.getNoticeUrl(),
                PayConstant.LOCAL,
                System.currentTimeMillis()
        );
        CreateOrderResult result=syncRequestClient.createOrderByCurrency(orderRequest);
        String payUrl="";
        if (null!=result){
            SortedMap<Object,Object> repoParameters=new TreeMap<Object,Object>();
            repoParameters.put("mch_id",result.getMch_id());
            repoParameters.put("nonce_str",result.getNonceStr());
            repoParameters.put("trade_type",result.getTrade_type());
            repoParameters.put("pay_url",result.getPay_url());
            repoParameters.put("transaction_id",result.getTransaction_id());
            String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),repoParameters);
            if (result.getSign().equals(sign)){
                payUrl=result.getPay_url();
            }else {
                System.out.println("------------------签名不正确-------------------");
            }
        }
        CreateOrderResponse orderResponse=new CreateOrderResponse();
        orderResponse.setOrderId(orderId);
        orderResponse.setPayUrl(payUrl);
        response.setResult(orderResponse);
        return response;
    }

    /**
     * 数字货币充币请求接口
     * @return
     */
    @PostMapping("createOrderByCoin")
    public CommonResponse createOrderByCoin(@RequestBody WebTestRequest request) throws Exception {
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        CommonResponse response=new CommonResponse();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                payInfoProperties.getAppKey(), payInfoProperties.getSecretKey(),payInfoProperties.getMchKey(),options);

        String orderId=UUID.randomUUID().toString().replace("-","").toUpperCase();
        String ip="127.0.0.1";
        String deviceType="pc";

        NewOrderRequest orderRequest=new NewOrderRequest(
                deviceType,
                UUID.randomUUID().toString().replace("-","").toUpperCase(),
                orderId,
                ip,
//                PayConstant.COIN_TYPE,
                request.getCoinName(),
                PayConstant.TRADE_TYPE,
                PayConstant.COMMODITY_NAME,
//                BigDecimal.valueOf(100),
                request.getNum(),
                payInfoProperties.getNoticeUrl(),
                PayConstant.LOCAL,
                System.currentTimeMillis()
        );

        CreateOrderResult result=syncRequestClient.createOrderByCoin(orderRequest);
        String payUrl="";
        if (null!=result){
            SortedMap<Object,Object> repoParameters=new TreeMap<Object,Object>();
            repoParameters.put("mch_id",result.getMch_id());
            repoParameters.put("nonce_str",result.getNonceStr());
            repoParameters.put("trade_type",result.getTrade_type());
            repoParameters.put("pay_url",result.getPay_url());
            repoParameters.put("transaction_id",result.getTransaction_id());
            String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),repoParameters);
            if (result.getSign().equals(sign)){
                payUrl=result.getPay_url();
            }else {
                System.out.println("------------------签名不正确-------------------");
            }
        }
        CreateOrderResponse orderResponse=new CreateOrderResponse();
        orderResponse.setOrderId(orderId);
        orderResponse.setPayUrl(payUrl);
        response.setResult(orderResponse);
        return response;
    }

    /**
     * 法币提币请求接口
     * @return
     */
    @PostMapping("withdrawByCurrency")
    public WithdrawResult withdrawByCurrency() throws Exception {
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                payInfoProperties.getAppKey(), payInfoProperties.getSecretKey(),payInfoProperties.getMchKey(),options);
        String orderId=UUID.randomUUID().toString().replace("-","");
        String ip="127.0.0.1";
        WithdrawRequest request=new WithdrawRequest(
                orderId,
                "138********",
                "0086",
                PayConstant.CURRENCY_TYPE,
                PayConstant.TRADE_TYPE,
                BigDecimal.valueOf(1),
                payInfoProperties.getNoticeUrl(),
                String.valueOf( System.currentTimeMillis()),
                UUID.randomUUID().toString().replace("-",""),
                ip
        );
        WithdrawResult result=syncRequestClient.withdrawByCurrency(request);
        if (result!=null){
            SortedMap<Object,Object> repoParameters=new TreeMap<Object,Object>();
            repoParameters.put("mch_id",result.getMch_id());
            repoParameters.put("customer_id",result.getCustomer_id());
            repoParameters.put("nonce_str",result.getNonce_str());
            repoParameters.put("trade_type",result.getTrade_type());
            repoParameters.put("transaction_id",result.getTransaction_id());
            String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),repoParameters);
            if (!result.getSign().equals(sign)){
                System.out.println("------------------签名不正确-------------------");
            }
        }
        return result;
    }

    /**
     * 数字货币提币请求接口
     * @return
     */
    @PostMapping("withdrawByCoin")
    public WithdrawResult withdrawByCoin() throws Exception {
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                payInfoProperties.getAppKey(), payInfoProperties.getSecretKey(),payInfoProperties.getMchKey(),options);
        String orderId=UUID.randomUUID().toString().replace("-","");
        String ip="127.0.0.1";
        WithdrawRequest request=new WithdrawRequest(
                orderId,
                "138********",
                "0086",
                PayConstant.COIN_TYPE,
                PayConstant.TRADE_TYPE,
                BigDecimal.valueOf(1),
                payInfoProperties.getNoticeUrl(),
                String.valueOf( System.currentTimeMillis()),
                UUID.randomUUID().toString().replace("-",""),
                ip
        );
        WithdrawResult result=syncRequestClient.withdrawByCoin(request);
        if (result!=null){
            SortedMap<Object,Object> repoParameters=new TreeMap<Object,Object>();
            repoParameters.put("mch_id",result.getMch_id());
            repoParameters.put("customer_id",result.getCustomer_id());
            repoParameters.put("nonce_str",result.getNonce_str());
            repoParameters.put("trade_type",result.getTrade_type());
            repoParameters.put("transaction_id",result.getTransaction_id());
            String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),repoParameters);
            if (!result.getSign().equals(sign)){
                System.out.println("------------------签名不正确-------------------");
            }
        }
        return result;
    }



    /**
     * 账户余额信息查询接口
     * @return
     */
    @GetMapping("queryAccount")
    public Account queryAccount() throws Exception {
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                payInfoProperties.getAppKey(), payInfoProperties.getSecretKey(),payInfoProperties.getMchKey(),options);
        GetAccountRequest request=new GetAccountRequest(
                UUID.randomUUID().toString().replace("-",""),
                PayConstant.ACCOUN_TYPE
        );
        Account result=syncRequestClient.getAccount(request);
        if (result!=null){
            SortedMap<Object,Object> repoParameters=new TreeMap<Object,Object>();
            repoParameters.put("mch_id",result.getMch_id());
            repoParameters.put("nonce_str",result.getNonce_str());
            repoParameters.put("balance",result.getBalance());
            repoParameters.put("freeze",result.getFreeze());
            String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),repoParameters);
            if (!result.getSign().equals(sign)){
                System.out.println("------------------签名不正确-------------------");
            }
        }
        return result;

    }

    /**
     * 充币订单信息查询接口
     * @return
     */
    @GetMapping("queryOrder/{orderId}")
    public CommonResponse queryOrder(@PathVariable String orderId) throws Exception {
        RequestOptions options = new RequestOptions();
        options.setUrl(payInfoProperties.getTradeUrl());
        CommonResponse response=new CommonResponse();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                payInfoProperties.getAppKey(), payInfoProperties.getSecretKey(),payInfoProperties.getMchKey(),options);
        QueryOrderRequest request=new QueryOrderRequest(
//                PayConstant.ORDER_NO,
                orderId,
                UUID.randomUUID().toString().replace("-","")
        );
        QueryOrderResult result=syncRequestClient.queryOrder(request);
        String trade_state="";
        if (null!=result){
            SortedMap<Object,Object> repoParameters=new TreeMap<Object,Object>();
            repoParameters.put("nonce_str",result.getNonce_str());
            if (StringUtils.isNotBlank(result.getCustomer_id())){
                repoParameters.put("customer_id",result.getCustomer_id());
            }
            repoParameters.put("transaction_id",result.getTransaction_id());
            repoParameters.put("out_trade_no",result.getOut_trade_no());
            repoParameters.put("mch_id",result.getMch_id());
            repoParameters.put("total_amount",result.getTotal_amount());
            repoParameters.put("trade_type",result.getTrade_type());
            repoParameters.put("fee_type",result.getFee_type());
            repoParameters.put("trade_state",result.getTrade_state());
            repoParameters.put("tick_price",result.getTick_price());
            repoParameters.put("order_fee",result.getOrder_fee());
            repoParameters.put("trade_state_desc",result.getTrade_state_desc());
            if (StringUtils.isNotBlank(result.getAttach())){
                repoParameters.put("attach",result.getAttach());
            }
            String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),repoParameters);
            if (result.getSign().equals(sign)){
                trade_state=result.getTrade_state();
            }else {
                System.out.println("------------------签名不正确-------------------");
            }

        }
        response.setResult(trade_state);
        return response;

    }




    @PostMapping("noticeReturn")
    public CommonResponse noticeReturn(@RequestBody NoticeRequest request) throws Exception {
        CommonResponse response=new CommonResponse();
        System.out.println("fee_type: "+request.getFee_type());
        System.out.println("total_amount: "+request.getTotal_amount());
        System.out.println("out_trade_no: "+request.getOut_trade_no());
        System.out.println("sign: "+request.getSign());
        System.out.println("transaction_id: "+request.getTransaction_id());
        System.out.println("trade_state_desc: "+request.getTrade_state_desc());
        System.out.println("time_end: "+request.getTime_end());
        System.out.println("trade_type: "+request.getTrade_type());
        System.out.println("trade_state: "+request.getTrade_state());
        System.out.println("tick_price: "+request.getTick_price());
        System.out.println("order_fee: "+request.getOrder_fee());
        System.out.println("nonce_str: "+request.getNonce_str());
        System.out.println("mch_id: "+request.getMch_id());
        System.out.println("attach: "+request.getAttach());
        System.out.println("customer_id: "+request.getCustomer_id());

        SortedMap<Object, Object> parameters=new TreeMap<Object, Object>();
        parameters.put("mch_id",request.getMch_id());
        parameters.put("nonce_str",request.getNonce_str());
        if (StringUtils.isNotBlank(request.getCustomer_id())){
            parameters.put("customer_id",request.getCustomer_id());
        }
        if (StringUtils.isNotBlank(request.getAttach())){
            parameters.put("attach",request.getAttach());
        }
        parameters.put("fee_type",request.getFee_type());
        parameters.put("total_amount",request.getTotal_amount());
        parameters.put("out_trade_no",request.getOut_trade_no());
        parameters.put("transaction_id",request.getTransaction_id());
        parameters.put("time_end",request.getTime_end());
        parameters.put("trade_type",request.getTrade_type());
        parameters.put("trade_state",request.getTrade_state());
        parameters.put("tick_price",request.getTick_price());
        parameters.put("order_fee",request.getOrder_fee());
        parameters.put("trade_state_desc",request.getTrade_state_desc());
        String sign= paySign.createPaySign(payInfoProperties.getSecretKey(),parameters);
        if (request.getSign().equals(sign)){
            System.out.println("------------------签名正确-------------------");
        }else {
            System.out.println("------------------签名不正确-------------------");
        }
        return response;
    }

    public static void main(String[] a){

//        ClientController controller=new ClientController();

//        List<CoinResult> results=controller.currencyList();


//        List<CoinResult> results=controller.coinList();

//        QueryOrderResult result=controller.queryOrder();

//        CreateOrderResult result=controller.createOrderByCoin();

//        WithdrawResult result=controller.withdrawByCurrency();


//        AsyncRequestClient asyncRequestClient = AsyncRequestClient.create(PayConstant.APP_KEY, PayConstant.SECRET_KEY,PayConstant.MCH_KEY);
//        asyncRequestClient.getCoinList( (statisticsResult) -> {
//            if (statisticsResult.succeeded()) {
//                System.out.println("---- Statistics ----");
//                for (CoinResult result:statisticsResult.getData()){
//                    System.out.println("feeName: " + result.getFee_name());
//                    System.out.println("feeType: " + result.getFee_type());
//                    System.out.println();
//                }
//            }
//        });

//        AsyncRequestClient asyncRequestClient = AsyncRequestClient.create(PayConstant.APP_KEY, PayConstant.SECRET_KEY,PayConstant.MCH_KEY);
//        String orderId=UUID.randomUUID().toString().replace("-","");
//        String ip="127.0.0.1";
//        String deviceType="pc";
//
//        NewOrderRequest orderRequest=new NewOrderRequest(
//                deviceType,
//                UUID.randomUUID().toString().replace("-",""),
//                orderId,
//                ip,
//                PayConstant.CURRENCY_TYPE,
//                PayConstant.TRADE_TYPE,
//                PayConstant.COMMODITY_NAME,
//                BigDecimal.valueOf(0.001),
//                PayConstant.NOTICE_URL,
//                PayConstant.LOCAL,
//                System.currentTimeMillis()
//        );
//        asyncRequestClient.createOrderByCoin(orderRequest, (statisticsResult) -> {
//            if (statisticsResult.succeeded()) {
//                System.out.println("---- Statistics ----");
//                System.out.println("nonceStr: " + statisticsResult.getData().getNonceStr());
//                System.out.println("payUrl: " + statisticsResult.getData().getPay_url());
//                System.out.println("tradeType: " + statisticsResult.getData().getTrade_type());
//                System.out.println("mchId: " + statisticsResult.getData().getMch_id());
//                System.out.println("sign: " + statisticsResult.getData().getSign());
//            }
//        });
//
//       AsyncRequestClient asyncRequestClient = AsyncRequestClient.create(PayConstant.APP_KEY, PayConstant.SECRET_KEY,PayConstant.MCH_KEY);
//        String orderId=UUID.randomUUID().toString().replace("-","");
//        String ip="127.0.0.1";
//        WithdrawRequest request=new WithdrawRequest(
//                orderId,
//                "13866668888",
//                "0086",
//                PayConstant.COIN_TYPE,
//                PayConstant.TRADE_TYPE,
//                BigDecimal.valueOf(0.002),
//                PayConstant.NOTICE_URL,
//                String.valueOf( System.currentTimeMillis()),
//                UUID.randomUUID().toString().replace("-",""),
//                ip
//        );
//        asyncRequestClient.withdrawByCoin(request, (statisticsResult) -> {
//            if (statisticsResult.succeeded()) {
//                System.out.println("---- Statistics ----");
//                System.out.println("nonceStr: " + statisticsResult.getData().getNonce_str());
//                System.out.println("customerId: " + statisticsResult.getData().getCustomer_id());
//                System.out.println("tradeType: " + statisticsResult.getData().getTrade_type());
//                System.out.println("mchId: " + statisticsResult.getData().getMch_id());
//                System.out.println("sign: " + statisticsResult.getData().getSign());
//            }
//        });

    }



}
