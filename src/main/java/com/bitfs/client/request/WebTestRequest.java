package com.bitfs.client.request;

import java.math.BigDecimal;

public class WebTestRequest {

    public WebTestRequest() {
    }

    public WebTestRequest(BigDecimal num, String coinName) {
        this.num = num;
        this.coinName = coinName;
    }

    private BigDecimal num;

    private String coinName;

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }
}
