package com.bitfs.client.model;

import com.bitfs.client.exception.BitfsApiException;

import java.net.URL;

/**
 * The configuration for the request APIs
 */
public class RequestOptions {

  private String url = "";

  public RequestOptions() {
  }

  public RequestOptions(RequestOptions option) {
    this.url = option.url;
  }

  /**
   * Set the URL for request.
   *
   * @param
   */
  public void setUrl(String url) {
    try {
      URL u = new URL(url);
    } catch (Exception e)
    {
      throw new BitfsApiException(
          BitfsApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
    }
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
