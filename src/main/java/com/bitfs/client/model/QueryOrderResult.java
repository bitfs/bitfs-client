package com.bitfs.client.model;


public class QueryOrderResult {

    public QueryOrderResult() {
    }

    public QueryOrderResult(String mch_id, String customer_id, String out_trade_no, String nonce_str, String total_amount, String transaction_id, String trade_type, String fee_type, String attach, String trade_state, String trade_state_desc, String tick_price, String order_fee, String sign) {
        this.mch_id = mch_id;
        this.customer_id = customer_id;
        this.out_trade_no = out_trade_no;
        this.nonce_str = nonce_str;
        this.total_amount = total_amount;
        this.transaction_id = transaction_id;
        this.trade_type = trade_type;
        this.fee_type = fee_type;
        this.attach = attach;
        this.trade_state = trade_state;
        this.trade_state_desc = trade_state_desc;
        this.tick_price = tick_price;
        this.order_fee = order_fee;
        this.sign = sign;
    }

    private String mch_id;//商户id

    private String customer_id; //用户id

    private String out_trade_no;//商户订单号

    private String nonce_str; //随机字符串

    private String total_amount; //订单数量

    private String transaction_id;//bitfs订单号

    private String trade_type;//交易类型

    private String fee_type;//交易币种

    private String attach;//附加信息

    private String trade_state;//交易状态

    private String trade_state_desc; //交易状态描述

    private String tick_price; //行情价

    private String order_fee; //订单数量

    private String sign; //签名

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

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

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getTick_price() {
        return tick_price;
    }

    public void setTick_price(String tick_price) {
        this.tick_price = tick_price;
    }

    public String getOrder_fee() {
        return order_fee;
    }

    public void setOrder_fee(String order_fee) {
        this.order_fee = order_fee;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
