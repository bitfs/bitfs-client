package com.bitfs.client.request;

public class GetAccountRequest {

    public GetAccountRequest() {
    }

    public GetAccountRequest(String nonce_str, String account_type) {
        this.nonce_str = nonce_str;
        this.account_type = account_type;
    }

    private String nonce_str; //随机字符串

    private String account_type; //币类型

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
