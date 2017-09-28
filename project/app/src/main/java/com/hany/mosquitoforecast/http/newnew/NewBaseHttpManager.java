package com.hany.mosquitoforecast.http.newnew;

import com.hany.mosquitoforecast.http.HttpRequestAsyncTask;
import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.http.RequestInfo;
import com.hany.mosquitoforecast.http.frame.IHttpBasic;
import com.hany.mosquitoforecast.util.LogManager;

import java.util.concurrent.ExecutionException;

/**
 * Created by HanyLuv on 2016-08-16.
 */
public abstract class NewBaseHttpManager<T> implements INewHttpBasic<T> {
    @Override
    public void request(RequestInfo requestInfo, HttpRequestResultListener resultListener) {
        new NewHttpRequestAsyncTask(resultListener).execute(requestInfo);
    }
}
