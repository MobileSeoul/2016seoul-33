package com.hany.mosquitoforecast.http.newnew;

import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.http.RequestInfo;

/**
 * Created by hany on 16. 8. 4..
 */
public interface INewHttpBasic<T> {
    //api를 요청한다.
    void request(RequestInfo requestInfo,HttpRequestResultListener resultListener);
}
