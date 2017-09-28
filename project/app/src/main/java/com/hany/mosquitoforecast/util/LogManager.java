package com.hany.mosquitoforecast.util;

import android.util.Log;

/**
 * Created by HanyLuv on 2016-08-03.
 */
public class LogManager {
    private static final String TAG = "HANY'S PROJECT LOG";
    private static boolean isShowing = true;

    public static final void e(String msg) {
        if (isShowing) {
            Log.e(TAG, msg);
        }
    }
}
