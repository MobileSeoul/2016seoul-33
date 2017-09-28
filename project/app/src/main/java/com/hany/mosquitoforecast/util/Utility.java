package com.hany.mosquitoforecast.util;

import android.text.Html;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by HanyLuv on 2016-08-23.
 */
public class Utility {
    private Utility() {
    }

    public static String clearHTMLTags(String htmlStr) {
        String temp = "";
        if (TextUtils.isEmpty(htmlStr)) {
            return temp;
        }
        temp = Html.fromHtml(htmlStr).toString().replaceAll("\n", "");
        return temp;
    }

    public static String convertingUTF8(String str) {
//        byte[] utf8StringBuffer = str.getBytes(Charset.forName("utf-8"));
//        return utf8StringBuffer.toString();
        String utf8Str = "";
        CharBuffer cbuffer = null;
        Charset utf8charset = null;
        try {
            cbuffer = CharBuffer.wrap((new String(str.getBytes(), "EUC-KR")).toCharArray());
            utf8charset = Charset.forName("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (utf8charset != null) {
            ByteBuffer bbuffer = utf8charset.encode(cbuffer);
            utf8Str = new String(bbuffer.array());
        }
        return utf8Str;
    }
}
