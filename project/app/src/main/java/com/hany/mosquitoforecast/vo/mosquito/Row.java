package com.hany.mosquitoforecast.vo.mosquito;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by HanyLuv on 2016-08-03.
 */
@Root (name = "row")
public class Row {
    @Element (name = "MOSQUITO_DATE")
    private String mosquitoDate;
    @Element (name = "MOSQUITO_VALUE")
    private String mosquitoValue;

    public String getMosquitoDate() {
        return mosquitoDate;
    }

    public void setMosquitoDate(String mosquitoDate) {
        this.mosquitoDate = mosquitoDate;
    }

    public String getMosquitoValue() {
        return mosquitoValue;
    }

    public void setMosquitoValue(String mosquitoValue) {
        this.mosquitoValue = mosquitoValue;
    }
}
