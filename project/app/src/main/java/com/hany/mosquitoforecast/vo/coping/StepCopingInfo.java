package com.hany.mosquitoforecast.vo.coping;

/**
 * Created by HanyLuv on 2016-08-25.
 */
public class StepCopingInfo {
    private String mStepDefence;
    private String mStepActive;

    public StepCopingInfo(String mStepDefence, String mStepActive) {
        this.mStepDefence = mStepDefence;
        this.mStepActive = mStepActive;
    }

    public String getStepDefence() {
        return mStepDefence;
    }

    public void setStepDefence(String mStepDefence) {
        this.mStepDefence = mStepDefence;
    }

    public String getStepActive() {
        return mStepActive;
    }

    public void setmStepActive(String mStepActive) {
        this.mStepActive = mStepActive;
    }
}
