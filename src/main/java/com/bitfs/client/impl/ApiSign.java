package com.bitfs.client.impl;

import com.bitfs.client.exception.BitfsApiException;
import com.bitfs.client.utils.MD5Kit;
import com.bitfs.client.utils.UrlParamsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

class ApiSign {

  private static final String mchId = "mch_id";
  private static final String accessKey = "appKey";
  private static final String signValue = "md5";
  private static final String signMethod = "sign_type";
  private static final String signature = "sign";


  void createSignature(String appKey, String secretKey, String mchKey,UrlParamsBuilder builder) {

    if (appKey == null || "".equals(appKey) || secretKey == null || "".equals(secretKey)) {
      throw new BitfsApiException(BitfsApiException.KEY_MISSING,
          "API key and secret key are required");
    }

    builder.putToPost(mchId,mchKey)
        .putToPost(accessKey, appKey)
        .putToPost(signMethod, signValue);
    String payload = builder.buildSignature(secretKey);
    String sign="";
    try {
      sign=MD5Kit.encodeMD5Hex(payload).toUpperCase();
    } catch (Exception e) {
      throw new BitfsApiException(BitfsApiException.RUNTIME_ERROR,
          "[sign] sign error: " + e.getMessage());
    }
    builder.putToPost(signature, sign);

  }

}
