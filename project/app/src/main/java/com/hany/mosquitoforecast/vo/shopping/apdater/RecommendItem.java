package com.hany.mosquitoforecast.vo.shopping.apdater;

import com.hany.mosquitoforecast.adapter.CopingListAdapter;
import com.hany.mosquitoforecast.adapter.RecommendRecyclerItemAdapter;

/**
 * Created by HanyLuv on 2016-09-27.
 */

public class RecommendItem {

    private String mSearchKey; //타이틀로 사용될것.
    private RecommendRecyclerItemAdapter mRecommendRecyclerItemAdapter;

    public RecommendItem(RecommendRecyclerItemAdapter recommendRecyclerItemAdapter, String searchKey) {
        mRecommendRecyclerItemAdapter = recommendRecyclerItemAdapter;
        mSearchKey = searchKey;

    }

    public String getSearchKey() {
        return mSearchKey;
    }

    public RecommendRecyclerItemAdapter getRecommendItemAdapter() {
        return mRecommendRecyclerItemAdapter;
    }
}
