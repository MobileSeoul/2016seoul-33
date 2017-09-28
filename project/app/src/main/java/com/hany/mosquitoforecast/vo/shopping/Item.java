package com.hany.mosquitoforecast.vo.shopping;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by HanyLuv on 2016-08-16.
 */
@Root
public class Item {
    @Element
    private String title;

    @Element
    private String link;

    @Element
    private String image;

    @Element
    private int lprice;

    @Element
    private int hprice;

    @Element
    private String mallName;

    @Element
    private String productId;

    @Element
    private String productType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLprice() {
        return lprice;
    }

    public void setLprice(int lprice) {
        this.lprice = lprice;
    }

    public int getHprice() {
        return hprice;
    }

    public void setHprice(int hprice) {
        this.hprice = hprice;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
