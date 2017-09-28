package com.hany.mosquitoforecast.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by HanyLuv on 2016-10-03.
 */

public class BrowserActivityController {

    public static void startBrowser(Context context, String strUrl) {
        if (context == null || TextUtils.isEmpty(strUrl)) {
            return;
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUrl));
        LogManager.e("link : " + strUrl);
        context.startActivity(browserIntent);
    }
}
