package com.bitfs.client.utils;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class PaySign {

    // 用StringBuffer 所有参与传参的key按照accsii排序（升序）
    public String createPaySign(String signKey, SortedMap<Object,Object> parameters) throws Exception {
        StringBuilder sb = new StringBuilder();
        Set es = parameters.keySet();
        for (Object set : es) {
            String k = set.toString();
            Object v = parameters.get(k);
            sb.append(k)
                    .append("=")
                    .append(v.toString())
                    .append("&");
        }
        sb.append("key=")
                .append(signKey);
        return MD5Kit.encodeMD5Hex(sb.toString()).toUpperCase();
    }


    public static String testPaySign(String signKey, SortedMap<Object,Object> parameters) throws Exception {
        StringBuilder sb = new StringBuilder();
        Set es = parameters.keySet();
        for (Object set : es) {
            String k = set.toString();
            Object v = parameters.get(k);
            sb.append(k)
                    .append("=")
                    .append(v.toString())
                    .append("&");
        }
        sb.append("key=")
                .append(signKey);
        return MD5Kit.encodeMD5Hex(sb.toString()).toUpperCase();
    }

}
