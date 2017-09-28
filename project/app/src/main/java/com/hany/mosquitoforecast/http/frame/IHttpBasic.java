package com.hany.mosquitoforecast.http.frame;

import com.hany.mosquitoforecast.http.RequestInfo;

/**
 * Created by hany on 16. 8. 4..
 */
public interface IHttpBasic<T> {
    //api를 요청한다.
    String request(RequestInfo requestInfo);
    //받아온 데이터를 알맞게 변환함
    T converting(String strResult);
}
