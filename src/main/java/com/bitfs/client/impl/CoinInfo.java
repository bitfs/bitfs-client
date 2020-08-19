package com.bitfs.client.impl;

import com.bitfs.client.SyncRequestClient;
import com.bitfs.client.constants.PayConstant;
import com.bitfs.client.model.CoinResult;
import com.bitfs.client.model.RequestOptions;

import java.util.List;


public class CoinInfo {

    public static boolean checkCoin(String coinName){
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(
                "", "","",options);
        List<CoinResult> coinResultList=syncRequestClient.getCurrencyList();
        boolean flag=false;
        for (CoinResult coinResult:coinResultList){
            if (coinResult.getFee_type().equals(coinName)){
                flag=true;
                break;
            }
        }
        return flag;
    }
}
