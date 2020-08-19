package com.bitfs.client.request;

public class NoticeRequest {

    public NoticeRequest() {
    }

    public NoticeRequest(String fee_type, String total_amount, String out_trade_no, String sign, String transaction_id, String trade_state_desc, String time_end, String trade_type, String trade_state, String tick_price, String order_fee, String nonce_str, String mch_id, String attach, String customer_id) {
        this.fee_type = fee_type;
        this.total_amount = total_amount;
        this.out_trade_no = out_trade_no;
        this.sign = sign;
        this.transaction_id = transaction_id;
        this.trade_state_desc = trade_state_desc;
        this.time_end = time_end;
        this.trade_type = trade_type;
        this.trade_state = trade_state;
        this.tick_price = tick_price;
        this.order_fee = order_fee;
        this.nonce_str = nonce_str;
        this.mch_id = mch_id;
        this.attach = attach;
        this.customer_id = customer_id;
    }



    private String fee_type;
    private String total_amount;
    private String out_trade_no;
    private String sign;
    private String transaction_id;
    private String trade_state_desc;
    private String time_end;
    private String trade_type;
    private String trade_state;
    private String tick_price;
    private String order_fee;
    private String nonce_str;
    private String mch_id;
    private String attach;
    private String customer_id;

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
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

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
