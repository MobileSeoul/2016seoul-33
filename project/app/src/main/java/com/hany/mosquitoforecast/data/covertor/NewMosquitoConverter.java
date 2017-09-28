package com.hany.mosquitoforecast.data.covertor;

import android.text.TextUtils;

import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.Key;
import com.hany.mosquitoforecast.vo.mosquito.MosquitoStatus;
import com.hany.mosquitoforecast.vo.mosquito.Result;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Created by HanyLuv on 2016-10-13.
 */

public class NewMosquitoConverter implements INewBaseConverter<MosquitoStatus> {

    @Override
    public MosquitoStatus converting(String strResult) {
        MosquitoStatus mosquitoStatus = null;
        if (!TextUtils.isEmpty(strResult)) {
            Serializer serializer = new Persister();
            try {
                mosquitoStatus = serializer.read(MosquitoStatus.class, strResult);
            } catch (Exception e) {
                e.printStackTrace();
                LogManager.e(e.getMessage());
            }
            //실패했을때.. 결과값이 달라서 -_-; 아래와같은 로직추가
            if (mosquitoStatus == null) {
                mosquitoStatus = new MosquitoStatus();
                mosquitoStatus.setResult(convertingFailedResult(strResult));
            }

            LogManager.e("Request Result : " + strResult);
        }

        return mosquitoStatus;
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

    //모기 측정값에 따른 단계값을 가져온다..
    public String getMosquitoStep(String mqValue) {
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
