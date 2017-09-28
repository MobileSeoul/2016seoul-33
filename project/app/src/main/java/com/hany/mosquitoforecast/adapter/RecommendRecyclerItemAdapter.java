package com.hany.mosquitoforecast.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hany.mosquitoforecast.R;
import com.hany.mosquitoforecast.util.BrowserActivityController;
import com.hany.mosquitoforecast.util.LogManager;
import com.hany.mosquitoforecast.util.Utility;
import com.hany.mosquitoforecast.vo.shopping.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanyLuv on 2016-09-27.
 */

public class RecommendRecyclerItemAdapter extends RecyclerView.Adapter<RecommendRecyclerItemAdapter.ViewHolder> {
    private Context mContext;
    private List<Item> mList;
    private LayoutInflater mInflater;

    public RecommendRecyclerItemAdapter(Context context, ArrayList<Item> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_recommend_recycler_item_detatil, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public Item getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        LogManager.e("item size " + mList.size());
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mShoppingImageView;
        private TextView mShoppingItemTitle;
        private TextView mShoppingItemRowPrice;
        private TextView mShoppingItemHighPrice;
        private View mlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mlayout = itemView;
            mShoppingImageView = (ImageView) itemView.findViewById(R.id.recycler_shopping_img);
            mShoppingItemTitle = (TextView) itemView.findViewById(R.id.recycler_shopping_title);
            mShoppingItemRowPrice = (TextView) itemView.findViewById(R.id.recycler_shopping_r_price);
            mShoppingItemHighPrice = (TextView) itemView.findViewById(R.id.recycler_shopping_h_price);
        }

        public void bind(final Item item) {
            Picasso.with(mContext).load(item.getImage()).placeholder(R.drawable.loading).into(mShoppingImageView);
            String shoppingTitle = clearHTMLTags(item.getTitle());
            mShoppingItemTitle.setText(shoppingTitle);
            mShoppingItemRowPrice.setText(checkPrice(item.getLprice()));
            mShoppingItemHighPrice.setText(checkPrice(item.getHprice()));

            mlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BrowserActivityController.startBrowser(mContext,item.getLink());
                }
            });
        }

        //가격정보 데이터가 0으로 내려오는 경우 정보없음으로 출력.
        private String checkPrice(int price) {
            String strPrice;
            if (price <= 0) {
                strPrice = "정보없음";
            } else {
                strPrice = String.valueOf(price);
            }
            return strPrice;
        }

        private String clearHTMLTags(String str) {
            return Utility.clearHTMLTags(str);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
