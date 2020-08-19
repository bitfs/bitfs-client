package com.bitfs.client.request;

import java.math.BigDecimal;

public class WithdrawRequest {

    public WithdrawRequest() {
    }

    public WithdrawRequest(String out_trade_no, String get_account, String area_code, String fee_type, String trade_type, BigDecimal total_amount, String notify_url, String timestamp, String nonce_str, String spbill_create_ip) {
        this.out_trade_no = out_trade_no;
        this.get_account = get_account;
        this.area_code = area_code;
        this.fee_type = fee_type;
        this.trade_type = trade_type;
        this.total_amount = total_amount;
        this.notify_url = notify_url;
        this.timestamp = timestamp;
        this.nonce_str = nonce_str;
        this.spbill_create_ip = spbill_create_ip;
    }

    private String out_trade_no;//商户订单号

    private String get_account; //提币账号

    private String area_code; //提币账号归属地

    private String fee_type;//货币类型

    private String trade_type; //交易类型

    private BigDecimal total_amount;//提币数量

    private String notify_url;//异步回调接口地址

    private String timestamp;//时间戳

    private String nonce_str; //随机字符串

    private String spbill_create_ip; //终端ip  必须传正确的用户端IP

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getGet_account() {
        return get_account;
    }

    public void setGet_account(String get_account) {
        this.get_account = get_account;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
}
