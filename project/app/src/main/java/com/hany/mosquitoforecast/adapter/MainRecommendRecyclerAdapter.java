package com.hany.mosquitoforecast.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.util.BrowserActivityController;
import com.hany.mosquitoforecast.util.Utility;
import com.hany.mosquitoforecast.vo.shopping.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HanyLuv on 2016-10-03.
 */

public class MainRecommendRecyclerAdapter extends RecyclerView.Adapter<MainRecommendRecyclerAdapter.ViewHolder> {

    private ArrayList<Item> mItems;
    private Context mContext;

    public MainRecommendRecyclerAdapter(Context context, ArrayList<Item> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public MainRecommendRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_recommend_shopping_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainRecommendRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private Item getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTitleView;
        private View mLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mLayout = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.main_recommend_img);
            mTitleView = (TextView) itemView.findViewById(R.id.main_recommend_title);
        }

        public void bind(final Item item) {
            Picasso.with(mContext).load(item.getImage()).placeholder(R.drawable.loading).into(mImageView);
            if (!TextUtils.isEmpty(item.getTitle())) {
                mTitleView.setText(item.getTitle());
            }
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //빠른개발을위해 임시로 네이버 검색링크 띄워줌...
                    String naverSearchUrl = mContext.getResources().getString(R.string.naver_search_link);
                    String url = new StringBuilder().append(naverSearchUrl).append(item.getTitle().replace("#","")).toString();
                    BrowserActivityController.startBrowser(mContext, url);
                }
            });
        }
    }
}
