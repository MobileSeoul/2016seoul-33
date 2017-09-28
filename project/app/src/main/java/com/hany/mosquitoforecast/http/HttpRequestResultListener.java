package com.hany.mosquitoforecast.http;

/**
 * Created by HanyLuv on 2016-10-12.
 */

public interface HttpRequestResultListener {
    public void onSuccess(String strJson);
    public void onFailed();
}
