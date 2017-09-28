package com.hany.mosquitoforecast.vo.coping;

/**
 * Created by 160229i-b on 16. 8. 17..
 */
public class CopingInfo {
/*
    <!--1단계-->
    <string name="define_1_step">- 지수범위 0~250\n- 야외에 모기활동이 거의 없음\n- 야외에 모기유충 서식지가 형성되지 않음</string>
    <string name="define_1_step_defense_bottom">- 없음</string>
    <string name="define_1_step_active_bottom">- 없음</string>

    <string name="define_1_step_defense_middle">- 없음</string>
    <string name="define_1_step_active_middle">- 생활주변 모기유충 서식지 관찰</string>

    <string name="define_1_step_defense_top">- 모기 침입통로 보완\n- 창문과 문에 방충망 사용하기</string>
    <string name="define_1_step_active_top">- 생활주변 모기유충 서식지 파악</string>
    <!--1단계-->
*/

    private String mStepDefaultInfo;
    private String mStepDefenceBottom;
    private String mStepActiveBottom;
    private String mStepDefenceMiddle;
    private String mStepActiveMiddle;
    private String mStepDefenceTop;
    private String mStepActiveTop;

    private String mStepBottom;
    private String mStepMiddle;
    private String mStepTop;



//    public CopingInfo(String mStepDefaultInfo, String mStepDefenceBottom, String mStepActiveBottom, String mStepDefenceMiddle, String mStepActiveMiddle, String mStepDefenceTop, String mStepActiveTop) {
//        this.mStepDefaultInfo = mStepDefaultInfo;
//        this.mStepDefenceBottom = mStepDefenceBottom;
//        this.mStepActiveBottom = mStepActiveBottom;
//        this.mStepDefenceMiddle = mStepDefenceMiddle;
//        this.mStepActiveMiddle = mStepActiveMiddle;
//        this.mStepDefenceTop = mStepDefenceTop;
//        this.mStepActiveTop = mStepActiveTop;
//    }

    public String getmStepTop() {
        return mStepTop;
    }

    public String getmStepBottom() {
        return mStepBottom;
    }

    public String getmStepMiddle() {
        return mStepMiddle;
    }

    //지수 범위,
    public CopingInfo(String mStepDefaultInfo, String mStepBottom, String mStepDefenceBottom, String mStepActiveBottom, String mStepMiddle,String mStepDefenceMiddle, String mStepActiveMiddle, String mStepTop,String mStepDefenceTop, String mStepActiveTop) {
        this.mStepDefaultInfo = mStepDefaultInfo;
        this.mStepDefenceBottom = mStepDefenceBottom;
        this.mStepActiveBottom = mStepActiveBottom;
        this.mStepDefenceMiddle = mStepDefenceMiddle;
        this.mStepActiveMiddle = mStepActiveMiddle;
        this.mStepDefenceTop = mStepDefenceTop;
        this.mStepActiveTop = mStepActiveTop;
        this.mStepBottom = mStepBottom;
        this.mStepMiddle = mStepMiddle;
        this.mStepTop = mStepTop;
    }

    public String getStepDefaultInfo() {
        return mStepDefaultInfo;
    }

    public void setStepDefaultInfo(String mStepDefaultInfo) {
        this.mStepDefaultInfo = mStepDefaultInfo;
    }

    public String getStepDefenceBottom() {
        return mStepDefenceBottom;
    }

    public void setStepDefenceBottom(String mStepDefenceBottom) {
        this.mStepDefenceBottom = mStepDefenceBottom;
    }

    public String getStepActiveBottom() {
        return mStepActiveBottom;
    }

    public void setStepActiveBottom(String mStepActiveBottom) {
        this.mStepActiveBottom = mStepActiveBottom;
    }

    public String getStepDefenceMiddle() {
        return mStepDefenceMiddle;
    }

    public void setStepDefenceMiddle(String mStepDefenceMiddle) {
        this.mStepDefenceMiddle = mStepDefenceMiddle;
    }

    public String getStepActiveMiddle() {
        return mStepActiveMiddle;
    }

    public void setStepActiveMiddle(String mStepActiveMiddle) {
        this.mStepActiveMiddle = mStepActiveMiddle;
    }

    public String getStepDefenceTop() {
        return mStepDefenceTop;
    }

    public void setStepDefenceTop(String mStepDefenceTop) {
        this.mStepDefenceTop = mStepDefenceTop;
    }

    public String getStepActiveTop() {
        return mStepActiveTop;
    }

    public void setStepActiveTop(String mStepActiveTop) {
        this.mStepActiveTop = mStepActiveTop;
    }
}
