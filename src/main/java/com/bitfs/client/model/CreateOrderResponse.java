package com.bitfs.client.model;

public class CreateOrderResponse {

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(String orderId, String payUrl) {
        this.orderId = orderId;
        this.payUrl = payUrl;
    }

    private String orderId;

    private String payUrl;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
