package com.bitfs.client.impl;

import com.bitfs.client.SyncRequestClient;
import com.bitfs.client.constants.PayConstant;
import com.bitfs.client.model.Account;
import com.bitfs.client.request.GetAccountRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountInfo {

    private static final Map<String,Account> accountMap=new HashMap<>();


    static Account setAccount(String appkey,RestApiRequestImpl requestImpl) {

        GetAccountRequest request=new GetAccountRequest(
                UUID.randomUUID().toString().replace("-",""),
                PayConstant.ACCOUN_TYPE
        );
        Account account = RestApiInvoker.callSync(requestImpl.getAccount(request));
        AccountInfo.accountMap.put(appkey,account);
        return account;
    }


    public static Account getAccount(String appkey){
        return accountMap.get(appkey);
    }
}
