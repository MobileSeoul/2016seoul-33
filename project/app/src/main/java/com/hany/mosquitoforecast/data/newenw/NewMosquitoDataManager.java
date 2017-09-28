package com.hany.mosquitoforecast.data.newenw;

import android.content.Context;
import android.text.TextUtils;

import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.http.RequestInfo;
import com.hany.mosquitoforecast.http.newnew.NewMosquitoHttpManager;
import com.hany.mosquitoforecast.util.DataManager;
import com.hany.mosquitoforecast.vo.Key;

/**
 * Created by hany on 16. 8. 8..
 */
public class NewMosquitoDataManager {
    private Context mContext;
    private NewMosquitoHttpManager mMosquitoHttpManager;
    private DataManager mDataManager;

    public NewMosquitoDataManager(Context context) {
        mContext = context;
        mMosquitoHttpManager = new NewMosquitoHttpManager(mContext);
        mDataManager = new DataManager();
    }

    /**
     * @author hany
     * @description 오늘의 모기데이터를 제공
     */
    public void requestTodayMosquitoStatus(HttpRequestResultListener resultListener) {
        String todayDate = mDataManager.getTodayDate();
        requestMosquitoStatus(todayDate,resultListener);
    }

    private String extractMonthAndDayFromDate(String date) {
        String resultDate = mDataManager.extractMonthAndDayFromDate(date);
        if (TextUtils.isEmpty(resultDate)) {
            return date;
        }
        return resultDate;

    }

    public void requestMosquitoStatus(String checkDate, HttpRequestResultListener resultListener) {
        if (!TextUtils.isEmpty(checkDate)) {
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setUrl(mMosquitoHttpManager.createURLString(checkDate));
            mMosquitoHttpManager.request(requestInfo, resultListener);
        }
    }



    //모기 측정값에 따른 단계를 가져온다.
    private String getMosquitoStep(String mqValue) {
        String mqStep = Key.STEP_NULL;
        if(TextUtils.isEmpty(mqValue)){
            return mqStep;
        }
        Float fMqValue = Float.valueOf(mqValue);
        if (fMqValue >= 0 && fMqValue <= 250) {
            mqStep = Key.STEP_ONE;
        } else if (fMqValue >= 251 && fMqValue <= 500) {
            mqStep = Key.STEP_TWO;
        } else if (fMqValue >= 501 && fMqValue <= 750) {
            mqStep = Key.STEP_THREE;
        } else if (fMqValue >= 751) {
            mqStep = Key.STEP_FOUR;
        }
        return mqStep;

    }

}
