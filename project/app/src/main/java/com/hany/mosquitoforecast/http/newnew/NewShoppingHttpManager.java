package com.hany.mosquitoforecast.http.newnew;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hany.mosquitoforecast.http.manager.BaseHttpManager;
import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.Key;
import com.hany.mosquitoforecast.vo.shopping.Channel;
import com.hany.mosquitoforecast.vo.shopping.Rss;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by HanyLuv on 2016-08-16.
 */
public class NewShoppingHttpManager extends NewBaseHttpManager<Channel> {
    private final String SHOPPING_BASE_URI = "https://openapi.naver.com/v1/search/shop.xml";
    private final int SHOPPING_ITEM_DEFAULT_COUNT = 7;

    private Context mContext;

    public NewShoppingHttpManager(Context context) {
        mContext = context;
    }

    /**
     * @param searchKey    조회할 쇼핑 아이템이름
     * @param sortType     조회할 쇼핑 아이템이름 null이 들어오면 유사도순으로 가져온다.
     * @param displayCount 가져올 갯수
     */
    public String createURLString(@NonNull String searchKey, Key.SortType sortType, int displayCount) {
        if (displayCount <=0 || displayCount >= 100) {
            displayCount = SHOPPING_ITEM_DEFAULT_COUNT;
        }

        String srtSortType = null;
        if (!TextUtils.isEmpty(searchKey)) {searchKey =transformUTF8(searchKey);}
        if (TextUtils.isEmpty(searchKey)) {return null;}
        if(sortType==null){srtSortType = getDefaultSortType();}
        return createURL(searchKey,srtSortType,displayCount);
    }

    private String createURL(String searchKey, String srtSortType,int displayCount){
        return new StringBuilder()
                .append(SHOPPING_BASE_URI)
                .append("?query=").append(searchKey)
                .append("&display=").append(displayCount)
                .append("&sort=").append(srtSortType).toString();
    }


    /**
     * @description utf8 변환
     **/
    private String transformUTF8(String str) {
        String utf8String = null;
        try {
            utf8String = URLEncoder.encode( str ,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogManager.e(e.getMessage());
        }
        return utf8String;
    }

    private String getDefaultSortType() {
        return Key.SortType.SIM.getSortType();
    }
//
//    @Override
//    public Channel converting(String strResult) {
//       Channel channels = null;
//        if (!TextUtils.isEmpty(strResult)) {
//            Serializer serializer = new Persister();
//            try {
//                channels = serializer.read(Rss.class, strResult).getChannel();
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogManager.e(e.getMessage());
//            }
//        }
//        return channels;
//    }

}
