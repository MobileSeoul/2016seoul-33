package com.hany.mosquitoforecast.data.covertor;

/**
 * Created by HanyLuv on 2016-10-13.
 */

public interface INewBaseConverter<T>  {
    //받아온 데이터를 알맞게 변환함
    T converting(String strResult);
}
