package com.hany.mosquitoforecast.http;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by HanyLuv on 2016-08-16.
 */
public class RequestInfo {
    private String url;
    private Map<String, String> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Iterator<String> getHeaderKeysIterator() {
        Iterator<String> keys = null;
        if ((headers != null) && (!headers.isEmpty()) && (headers.size() > 0)) {
            keys = headers.keySet().iterator();
        }
        return keys;
    }
}
