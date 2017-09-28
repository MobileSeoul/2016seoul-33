package com.hany.mosquitoforecast.data.newenw;

import android.content.Context;

import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.http.RequestInfo;
import com.hany.mosquitoforecast.http.newnew.NewShoppingHttpManager;
import com.hany.mosquitoforecast.vo.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://drive.google.com/open?id=0B5h4aIdRUxTVNHNlZjlad1Z2YzA
 * Created by HanyLuv on 2016-08-16.
 */

//모기대책관련 용품에대해 제공
public class NewShoppingDataManager {
    private Context mContext;
    private NewShoppingHttpManager shoppingHttpManager;
    private ArrayList<String> keyList;

    public NewShoppingDataManager(Context context) {
        mContext = context;
        shoppingHttpManager = new NewShoppingHttpManager(context);
    }

    public void requestMosquitoShoppingItemChannel(String searchKey, HttpRequestResultListener resultListener) {
        requestMosquitoShoppingItemChannel(searchKey, null, resultListener);
    }

    public void requestMosquitoShoppingItemChannel(String searchKey, int displayCount, HttpRequestResultListener resultListener) {
        requestMosquitoShoppingItemChannel(searchKey, null, displayCount, resultListener);
    }

    public void requestMosquitoShoppingItemChannel(String searchKey, Key.SortType sortType, HttpRequestResultListener resultListener) {
        requestMosquitoShoppingItemChannel(searchKey, sortType, 0, resultListener);
    }

    public void requestMosquitoShoppingItemChannel(String searchKey, Key.SortType sortType, int displayCount, HttpRequestResultListener resultListener) {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setUrl(shoppingHttpManager.createURLString(searchKey, sortType, displayCount));
        requestInfo.setHeaders(createHeaders());
        shoppingHttpManager.request(requestInfo, resultListener); //  결과값을 요청한 곳으로 넘긴다.
    }

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(Key.NAVER_CLIENT_ID, getNaverClientId());
        headers.put(Key.NAVER_CLIENT_SECRET, getNaverClientSecret());
        return headers;
    }


    private String getNaverClientId() {
        return mContext.getString(R.string.naver_client_id);
    }

    private String getNaverClientSecret() {
        return mContext.getString(R.string.naver_client_secret);
    }


}
