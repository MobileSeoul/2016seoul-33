package com.hany.mosquitoforecast.http.newnew;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.hany.mosquitoforecast.http.HttpRequestResultListener;
import com.hany.mosquitoforecast.http.RequestInfo;
import com.hany.mosquitoforecast.util.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hany on 16. 8. 5..
 */
public class NewHttpRequestAsyncTask extends AsyncTask<RequestInfo, Void, String> {
    private final String REQUEST_METHOD_GET = "GET";
    private final String ENCODING_TYPE_UTF_8 = "UTF-8";
    private HttpRequestResultListener mResultListener;


    public NewHttpRequestAsyncTask(HttpRequestResultListener httpRequestResultListener){
        mResultListener = httpRequestResultListener;
    }

    @Override
    protected String doInBackground(RequestInfo... params) {
        RequestInfo requestInfo = params[0];
        return readDataFromURl(requestInfo);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(!TextUtils.isEmpty(s)){
            mResultListener.onSuccess(s);
        }else{
            mResultListener.onFailed();
        }
    }

    private String readDataFromURl(RequestInfo requestInfo) {
        String strResult = null;
        try {
            HttpURLConnection httpURLConnection = createHttpURLConnection(requestInfo);
            httpURLConnection.connect();

            InputStream is = httpURLConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODING_TYPE_UTF_8));
            reader.toString();
            String strReadLine;
            while ((strReadLine = reader.readLine()) != null) {
                builder.append(strReadLine + "\n");
            }
            strResult = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            LogManager.e(e.getMessage());
        }
        return strResult;
    }

    private HttpURLConnection createHttpURLConnection(RequestInfo requestInfo) throws IOException {
        String strUrl = requestInfo.getUrl();
        LogManager.e("request url : "+strUrl);
        if (TextUtils.isEmpty(strUrl)) {
            throw new NullPointerException("데이터를 읽어올 URL 주소가 입력되지 않았습니다.");
        }
        HttpURLConnection httpURLConnection = null;
        URL url = new URL(strUrl);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(REQUEST_METHOD_GET);

        Map<String, String> headers = requestInfo.getHeaders();
        Iterator<String> keyIterator = requestInfo.getHeaderKeysIterator();
        if (((headers != null) && (!headers.isEmpty()) && (headers.size() > 0)) && (keyIterator != null)) {
            while (keyIterator.hasNext()) {
                String keyValue = keyIterator.next();
                httpURLConnection.setRequestProperty(keyValue, headers.get(keyValue));
                LogManager.e("헤더 설정!!! keyValue : " + keyValue + " / HeaderData : " + headers.get(keyValue));
            }
        }
        return httpURLConnection;
    }


}
