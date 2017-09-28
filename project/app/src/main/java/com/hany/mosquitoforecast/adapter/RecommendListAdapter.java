package com.hany.mosquitoforecast.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.util.BrowserActivityController;
import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.vo.shopping.apdater.RecommendItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanyLuv on 2016-09-27.
 */

public class RecommendListAdapter extends BaseAdapter {

    private Context mContext;
    private List<RecommendItem> mRecommendItems;
    private LayoutInflater mInflater;

    public RecommendListAdapter(Context context, ArrayList<RecommendItem> recommendItems) {
        mContext = context;
        mRecommendItems = recommendItems;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        LogManager.e("List Adapter size : " + mRecommendItems.size());
        return mRecommendItems.size();
    }

    @Override
    public RecommendItem getItem(int position) {
        return mRecommendItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LogManager.e("List Adapter size getView : " + position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.view_recommand_recycler_itme, parent, false);
            holder.recommendTitleTextView = (TextView) convertView.findViewById(R.id.recommend_title);
            holder.itemRecyclerView = (RecyclerView) convertView.findViewById(R.id.recommend_item_recycler_view);
            holder.recommendMoreTextView = (TextView) convertView.findViewById(R.id.recommend_more);
            if (holder.itemRecyclerView != null) {
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
//                layoutManager.setAutoMeasureEnabled(true);
                holder.itemRecyclerView.setLayoutManager(layoutManager);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RecommendItem recommenditem = getItem(position);
        RecommendRecyclerItemAdapter itemAdapter = recommenditem.getRecommendItemAdapter();
        holder.recommendTitleTextView.setText(recommenditem.getSearchKey());
        holder.recommendMoreTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naverSearchUrl = mContext.getResources().getString(R.string.naver_search_link);
                String url = new StringBuilder().append(naverSearchUrl).append(recommenditem.getSearchKey()).toString();
                BrowserActivityController.startBrowser(mContext, url);
            }
        });
        holder.itemRecyclerView.setAdapter(itemAdapter);

        return convertView;
    }

    private class ViewHolder {
        public RecyclerView itemRecyclerView;
        public TextView recommendTitleTextView;
        public TextView recommendMoreTextView;
    }
}
