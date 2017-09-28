package com.hany.mosquitoforecast.util;

import android.content.Context;
import android.view.View;

/**
 * Created by HanyLuv on 2016-08-23.
 * 하단 푸터 보이고 안보이고 역활하는 클래스
 */
public class ViewAnimationManager {

    private View mView;
    private Context mCotenxt;

    public ViewAnimationManager(Context context, View view) {
        mView = view;
        mCotenxt = context;
    }
}
