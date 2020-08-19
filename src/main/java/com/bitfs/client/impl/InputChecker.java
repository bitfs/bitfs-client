package com.bitfs.client.impl;

import com.bitfs.client.exception.BitfsApiException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputChecker {

  private static final String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\t";

  private static final InputChecker checkerInst;

  static {
    checkerInst = new InputChecker();
  }

  static InputChecker checker() {
    return checkerInst;
  }

  private boolean isSpecialChar(String str) {

    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.find();
  }

  <T> InputChecker shouldNotNull(T value, String name) {
    if (value == null) {
      throw new BitfsApiException(BitfsApiException.INPUT_ERROR,
          "[Input] " + name + " should not be null");
    }
    return checkerInst;
  }

  <T> InputChecker shouldNull(T value, String name) {
    if (value != null) {
      throw new BitfsApiException(BitfsApiException.INPUT_ERROR,
          "[Input] " + name + " should be null");
    }
    return checkerInst;
  }



}
