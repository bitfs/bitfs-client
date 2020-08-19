package com.bitfs.client.request;

import java.math.BigDecimal;

public class NewOrderRequest {

    public NewOrderRequest() {
    }

    public NewOrderRequest(String device_type, String nonce_str, String out_trade_no, String spbill_create_ip, String fee_type, String trade_type, String body, BigDecimal total_amount, String notify_url, String local,Long timestamp) {
        this.device_type = device_type;
        this.nonce_str = nonce_str;
        this.out_trade_no = out_trade_no;
        this.spbill_create_ip = spbill_create_ip;
        this.fee_type = fee_type;
        this.trade_type = trade_type;
        this.body = body;
        this.total_amount = total_amount;
        this.notify_url = notify_url;
        this.local = local;
        this.timestamp=timestamp;
    }

    public NewOrderRequest(String device_type, String attach, String nonce_str, String out_trade_no, String spbill_create_ip, String fee_type, String trade_type, String body, BigDecimal total_amount, String notify_url, String local,Long timestamp) {
        this.device_type = device_type;
        this.attach = attach;
        this.nonce_str = nonce_str;
        this.out_trade_no = out_trade_no;
        this.spbill_create_ip = spbill_create_ip;
        this.fee_type = fee_type;
        this.trade_type = trade_type;
        this.body = body;
        this.total_amount = total_amount;
        this.notify_url = notify_url;
        this.local = local;
        this.timestamp=timestamp;
    }

    private String device_type; //设备类型 phone 移动端  PC 电脑端

    private String attach; //附加数据 （非必要参数）

    private String nonce_str; //随机字符串

    private String out_trade_no;//商户订单号

    private String spbill_create_ip; //终端ip  必须传正确的用户端IP

    private String fee_type;//货币类型

    private String trade_type; //交易类型

    private String body;//商品名称

    private BigDecimal total_amount;//充币金额

    private String notify_url;//结果通知回调地址

    private String local; //本地语言

    private Long timestamp; //时间戳

    public Long getTimestamp() {
        return timestamp;
    }

    public String getDevice_type() {
        return device_type;
    }

    public String getAttach() {
        return attach;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public String getFee_type() {
        return fee_type;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public String getBody() {
        return body;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getLocal() {
        return local;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
