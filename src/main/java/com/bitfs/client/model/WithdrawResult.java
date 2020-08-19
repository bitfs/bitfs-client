package com.bitfs.client.model;

public class WithdrawResult {

    public WithdrawResult() {
    }

    public WithdrawResult(String sign, String mch_id, String trade_type, String nonce_str, String customer_id) {
        this.sign = sign;
        this.mch_id = mch_id;
        this.trade_type = trade_type;
        this.nonce_str = nonce_str;
        this.customer_id = customer_id;
    }

    private String sign; //签名

    private String mch_id; //商户id

    private String trade_type; //交易类型

    private String nonce_str; //随机字符串

    private String customer_id; //用户id

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
