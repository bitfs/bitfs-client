package com.bitfs.client.model;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateOrderResult {

    public CreateOrderResult() {
    }

    public CreateOrderResult(String sign, String pay_url, String mch_id, String trade_type, String nonceStr) {
        this.sign = sign;
        this.pay_url = pay_url;
        this.mch_id = mch_id;
        this.trade_type = trade_type;
        this.nonceStr = nonceStr;
    }

    public CreateOrderResult(String sign, String pay_url, String mch_id, String trade_type, String nonceStr, String customer_id) {
        this.sign = sign;
        this.pay_url = pay_url;
        this.mch_id = mch_id;
        this.trade_type = trade_type;
        this.nonceStr = nonceStr;
        this.customer_id = customer_id;
    }

    private String sign; //签名

    private String pay_url; //跳转地址

    private String mch_id; //商户id

    private String trade_type; //交易类型

    private String nonceStr;

    private String customer_id;


    public String getSign() {
        return sign;
    }

    public String getPay_url() {
        return pay_url;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setPay_url(String pay_url) {
        this.pay_url = pay_url;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
