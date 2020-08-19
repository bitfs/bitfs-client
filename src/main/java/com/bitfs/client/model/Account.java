package com.bitfs.client.model;

import java.math.BigDecimal;

public class Account {

    public Account() {
    }

    public Account(String accountType, BigDecimal balance, BigDecimal freeze, String mch_id, String sign, String nonce_str) {
        this.accountType = accountType;
        this.balance = balance;
        this.freeze = freeze;
        this.mch_id = mch_id;
        this.sign = sign;
        this.nonce_str = nonce_str;
    }

    private String accountType;

    private BigDecimal balance; //余额

    private BigDecimal freeze; // 冻结

    private String mch_id; //商户id

    private String sign; //签名

    private String nonce_str;//随机字符

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
}
