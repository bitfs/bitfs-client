package com.bitfs.client.request;

public class QueryOrderRequest {

    public QueryOrderRequest() {
    }

    public QueryOrderRequest(String out_trade_no, String nonce_str) {
        this.out_trade_no = out_trade_no;
        this.nonce_str = nonce_str;
    }

    private String out_trade_no;//商户订单号

    private String nonce_str; //随机字符串

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
}
