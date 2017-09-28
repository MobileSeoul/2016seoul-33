package com.hany.mosquitoforecast.vo.mosquito;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by HanyLuv on 2016-08-03.
 */
@Root (name = "RESULT")
public class Result {
    @Element (name = "CODE")
    private String code;
    @Element (name = "MESSAGE")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
