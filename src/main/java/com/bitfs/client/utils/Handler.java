package com.bitfs.client.utils;

@FunctionalInterface
public interface Handler<T> {

  void handle(T t);
}
