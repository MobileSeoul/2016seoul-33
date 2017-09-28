package com.hany.mosquitoforecast.data;

import android.content.Context;
import android.widget.TextView;

import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.vo.coping.CopingInfo;
import com.hany.mosquitoforecast.vo.coping.StepCopingInfo;

import java.util.ArrayList;

/**
 * Created by HanyLuv on 2016-08-17.
 * 단계별 대책
 */
public class CopingDataManager {

    private Context mContext;
    private ArrayList<CopingInfo> mCopingInfo;
    private ArrayList<StepCopingInfo> mCopingInfoForMain;

    public CopingDataManager(Context context) {
        mContext = context;
        mCopingInfo = createCopingInfoList();
        mCopingInfoForMain = createCopingInfoListForMain();
    }

    public StepCopingInfo getStepCopingInfo(String mqValue) {
        return mCopingInfoForMain.get(getMosquitoStepIndex(mqValue));
    }

    private ArrayList<StepCopingInfo> createCopingInfoListForMain() {
        ArrayList<StepCopingInfo> copingInfoForMain = new ArrayList<>();
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_one_step_defense_bottom), getString(R.string.define_one_step_active_bottom)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_one_step_defense_middle), getString(R.string.define_one_step_active_middle)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_one_step_defense_top), getString(R.string.define_one_step_active_top)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_two_step_defense_bottom), getString(R.string.define_two_step_active_bottom)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_two_step_defense_middle), getString(R.string.define_two_step_active_middle)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_two_step_defense_top), getString(R.string.define_two_step_active_top)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_three_step_defense_bottom), getString(R.string.define_three_step_active_bottom)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_three_step_defense_middle), getString(R.string.define_three_step_active_middle)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_three_step_defense_top), getString(R.string.define_three_step_active_top)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_four_step_defense_bottom), getString(R.string.define_four_step_active_bottom)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_four_step_defense_middle), getString(R.string.define_four_step_active_middle)));
        copingInfoForMain.add(new StepCopingInfo(getString(R.string.define_four_step_defense_top), getString(R.string.define_four_step_active_top)));
        return copingInfoForMain;
    }

    private String getString(int res) {
        return mContext.getString(res);
    }


    public ArrayList<CopingInfo> getCopingInfoList() {
        return mCopingInfo;
    }

    private ArrayList<CopingInfo> createCopingInfoList() {
        ArrayList<CopingInfo> copingInfos = new ArrayList<>();
        copingInfos.add(new CopingInfo(getString(R.string.define_one_step),
                getString(R.string.define_one_step_bottom),
                getString(R.string.define_one_step_defense_bottom),
                getString(R.string.define_one_step_active_bottom),
                getString(R.string.define_one_step_middle),
                getString(R.string.define_one_step_defense_middle),
                getString(R.string.define_one_step_active_middle),
                getString(R.string.define_one_step_top),
                getString(R.string.define_one_step_defense_top),
                getString(R.string.define_one_step_active_top)));

        copingInfos.add(new CopingInfo(getString(R.string.define_two_step),
                getString(R.string.define_one_step_bottom),
                getString(R.string.define_two_step_defense_bottom),
                getString(R.string.define_two_step_active_bottom),
                getString(R.string.define_two_step_middle),
                getString(R.string.define_two_step_defense_middle),
                getString(R.string.define_two_step_active_middle),
                getString(R.string.define_two_step_top),
                getString(R.string.define_two_step_defense_top),
                getString(R.string.define_two_step_active_top)));

        copingInfos.add(new CopingInfo(getString(R.string.define_three_step),
                getString(R.string.define_three_step_bottom),
                getString(R.string.define_three_step_defense_bottom),
                getString(R.string.define_three_step_active_bottom),
                getString(R.string.define_three_step_middle),
                getString(R.string.define_three_step_defense_middle),
                getString(R.string.define_three_step_active_middle),
                getString(R.string.define_three_step_top),
                getString(R.string.define_three_step_defense_top),
                getString(R.string.define_three_step_active_top)));

        copingInfos.add(new CopingInfo(getString(R.string.define_four_step),
                getString(R.string.define_four_step_bottom),
                getString(R.string.define_four_step_defense_bottom),
                getString(R.string.define_four_step_active_bottom),
                getString(R.string.define_four_step_middle),
                getString(R.string.define_four_step_defense_middle),
                getString(R.string.define_four_step_active_middle),
                getString(R.string.define_four_step_top),
                getString(R.string.define_four_step_defense_top),
                getString(R.string.define_four_step_active_top)));
        return copingInfos;
    }

    private int getMosquitoStepIndex(String mqValue) {
        int index = 0;
        Float fMqValue = Float.valueOf(mqValue);
        if (fMqValue >= 0 && fMqValue <= 83.3) {
            index = 0;
        } else if (fMqValue >= 83.4 && fMqValue <= 166.6) {
            index = 1;
        } else if (fMqValue >= 166.7 && fMqValue <= 250) {
            index = 2;
        } else if (fMqValue >= 250.1 && fMqValue <= 333.3) {
            index = 3;
        } else if (fMqValue >= 333.4 && fMqValue <= 416.6) {
            index = 4;
        } else if (fMqValue >= 416.7 && fMqValue <= 500) {
            index = 5;
        } else if (fMqValue >= 500.1 && fMqValue <= 583.3) {
            index = 6;
        } else if (fMqValue >= 583.4 && fMqValue <= 666.6) {
            index = 7;
        } else if (fMqValue >= 666.7 && fMqValue <= 750) {
            index = 8;
        } else if (fMqValue >= 750.1 && fMqValue <= 833.3) {
            index = 9;
        } else if (fMqValue >= 833.4 && fMqValue <= 916.6) {
            index = 10;
        } else if (fMqValue >= 916.7) {
            index = 11;
        }
        return index;
    }
}
