package com.bitfs.client.model;

public class CoinResult {

    public CoinResult() {
    }

    public CoinResult(String fee_name, String fee_type) {
        this.fee_name = fee_name;
        this.fee_type = fee_type;
    }

    private String fee_name; //币名

    private String fee_type; //币编码

    public String getFee_name() {
        return fee_name;
    }

    public void setFee_name(String fee_name) {
        this.fee_name = fee_name;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }
}
