package com.hany.mosquitoforecast.vo.shopping;

import android.support.annotation.AttrRes;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by 160229i-b on 16. 8. 17..
 */
@Root(name = "rss")
public class Rss {
    @Attribute
    private String version;

    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
