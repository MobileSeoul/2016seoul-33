package com.hany.mosquitoforecast.http.newnew;


import android.content.Context;
import android.text.TextUtils;

import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.http.manager.BaseHttpManager;
import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.mosquito.MosquitoStatus;
import com.hany.mosquitoforecast.vo.mosquito.Result;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Created by HanyLuv on 2016-08-03.
 * 모기 api에서 데이터 가지고옴.
 */
public class NewMosquitoHttpManager extends NewBaseHttpManager<MosquitoStatus> {
    private final String MOSQUITO_BASE_URI = "http://openapi.seoul.go.kr:8088/";
    private Context mContext;

    public NewMosquitoHttpManager(Context context) {
        mContext = context;
    }

    public String createURLString(String date) {
        if (TextUtils.isEmpty(date)) return null;
        return new StringBuilder()
                .append(MOSQUITO_BASE_URI)
                .append(getAuthKey())
                .append(getDefaultParams())
                .append(date).toString();
    }

    private String getAuthKey() {
        return mContext.getString(R.string.public_data_auth_key);
    }

    private String getDefaultParams() {
        return mContext.getString(R.string.default_params);
    }

    private Result convertingFailedResult(String strResult) {
        Result failMosquitoStatus = null;
        if (!TextUtils.isEmpty(strResult)) {
            Serializer serializer = new Persister();
            try {
                failMosquitoStatus = serializer.read(Result.class, strResult);
            } catch (Exception e) {
                e.printStackTrace();
                LogManager.e(e.getMessage());
            }
        }
        return failMosquitoStatus;
    }
}
