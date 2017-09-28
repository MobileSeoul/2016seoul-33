package com.hany.mosquitoforecast.http.manager;

import com.hany.mosquitoforecast.http.HttpRequestAsyncTask;
import com.hany.mosquitoforecast.http.RequestInfo;
import com.hany.mosquitoforecast.http.frame.IHttpBasic;
import com.hany.mosquitoforecast.util.LogManager;

import java.util.concurrent.ExecutionException;

/**
 * Created by HanyLuv on 2016-08-16.
 */
public abstract class BaseHttpManager<T> implements IHttpBasic<T>{
    @Override
    public String request(RequestInfo requestInfo) {
        String result = null;
        try {
            result = new HttpRequestAsyncTask().execute(requestInfo).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogManager.e(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            LogManager.e(e.getMessage());
        }
        return result;
    }
}
