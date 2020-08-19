package com.bitfs.client.utils;

public abstract class EtfResult {

  public static String checkResult(int code) {
    switch (code) {
      case 200:
        return "";
      case -1:
        return "System error";
    }
    return "";
  }

}
